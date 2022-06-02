package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.annotation.MethodType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ContractClient {

    private static boolean isDeclaringClassAnObject(Method method) {
        return method.getDeclaringClass() == Object.class;
    }

    public static ContractMethod getAnnotation(Method method) {
        for (Annotation annotation: method.getAnnotations()) {
            if (ContractMethod.class.isInstance(annotation)) {
                return (ContractMethod) annotation;
            }
        }
        throw new NearException("Contract method must have an annotation specifying the method type and name");
    }

    public static String getMethodName(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        if (contractMethod == null) {
            return method.getName();
        } else {
            return contractMethod.name();
        }
    }

    public static MethodType getMethodType(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        if (contractMethod == null) {
            return MethodType.VIEW;
        } else {
            return contractMethod.type();
        }
    }

    private static Object proxyObjectMethods(Method method, Object proxyObject, Object[] args) {
        String name = method.getName();
        if (name.equals("toString")) {
            return proxyObject.getClass().getName() + "@" + System.identityHashCode(proxyObject);
        }
        if (name.equals("hashCode")) {
            return System.identityHashCode(proxyObject);
        }
        if (name.equals("equals")) {
            return proxyObject == args[0];
        }
        throw new RuntimeException(method.getName() + " is not a member of java.lang.Object");
    }

    @SuppressWarnings("unchecked")
    public static <T> T createClientProxy(Class<T> proxyInterface, final ContractMethodProxy client) {

        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(), new Class<?>[]{proxyInterface}, (proxy, method, args) -> {
            if (isDeclaringClassAnObject(method)) return proxyObjectMethods(method, proxy, args);

            final NearClient nearClient = (NearClient) args[0];
            final String countractAccountId = (String) args[1];
            final ContractMethodParams arguments = (ContractMethodParams) args[2];
            final String methodName = getMethodName(method);
            final MethodType methodType = getMethodType(method);
            final Class<?> returnType = (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];

            return client.invoke(nearClient, countractAccountId, methodName, methodType, arguments, returnType);

        });
    }


}
