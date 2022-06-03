package com.syntifi.near.api.rpc.service.contract.common;

import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.param.ContractMethodParams;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

/**
 * Base creator of ContractClients
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public abstract class ContractClient {

    private static boolean isDeclaringClassAnObject(Method method) {
        return method.getDeclaringClass() == Object.class;
    }

    public static ContractMethod getAnnotation(Method method) {
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation instanceof ContractMethod) {
                return (ContractMethod) annotation;
            }
        }
        throw new NearException("Contract method must have an annotation specifying the method type and name");
    }

    public static String getMethodName(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        return contractMethod.name();
    }

    public static ContractMethodType getMethodType(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        return contractMethod.type();
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

    /**
     * Creates a proxy for a given interface
     *
     * @param proxyInterface the proxy interface
     * @param client         the client to use
     * @param <T>            the type of the proxy class
     * @return a proxy client instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T createClientProxy(Class<T> proxyInterface, final ContractMethodProxy client) {

        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(), new Class<?>[]{proxyInterface}, (proxy, method, args) -> {
            if (isDeclaringClassAnObject(method)) return proxyObjectMethods(method, proxy, args);

            // TODO: Improve on how to get the variables needed for the call
            final NearClient nearClient = (NearClient) args[0];
            final String countractAccountId = (String) args[1];
            final ContractMethodParams arguments = args.length > 2 ? (ContractMethodParams) args[2] : null;
            final String methodName = getMethodName(method);
            final ContractMethodType methodType = getMethodType(method);
            final Class<?> returnType = (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];

            return client.invoke(nearClient, countractAccountId, methodName, methodType, arguments, returnType);

        });
    }
}
