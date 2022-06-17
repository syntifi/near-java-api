package com.syntifi.near.api.rpc.service.contract.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntifi.near.api.common.exception.NearException;
import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameterType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

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

    public static ContractParameter getAnnotation(Parameter parameter) {
        for (Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof ContractParameter) {
                return (ContractParameter) annotation;
            }
        }
        throw new NearException("Contract parameter is expected have an annotation specifying its name");
    }

    public static String getMethodName(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        return contractMethod.name();
    }

    public static ContractMethodType getMethodType(Method method) {
        final ContractMethod contractMethod = getAnnotation(method);
        return contractMethod.type();
    }

    public static String getParameterName(Annotation annotation) {
        final ContractParameter contractParameter = (ContractParameter) annotation;
        return contractParameter.value();
    }

    public static ContractParameterType[] getParameterType(Annotation annotation) {
        final ContractParameter contractParameter = (ContractParameter) annotation;
        return contractParameter.type();
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

            final NearClient nearClient = (NearClient) args[0];
            final String contractAccountId = (String) args[1];
            final String methodName = getMethodName(method);
            final ContractMethodType methodType = getMethodType(method);

            if (args.length < 3) {
                final Class<?> returnType = (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
                Base64String arguments = Base64String.fromDecodedString("");
                return client.invoke(nearClient, contractAccountId, methodName, methodType, arguments, returnType);
            } else {
                PrivateKey privateKey = null;
                PublicKey publicKey = null;
                String accountId = null;
                BigInteger deposit = BigInteger.valueOf(1L);
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode arguments = mapper.createObjectNode();
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (int i = 2; i < args.length; i++) {
                    Object arg = args[i];
                    String parameterName = getParameterName(parameterAnnotations[i][0]);
                    List<ContractParameterType> parameterType = Arrays.asList(getParameterType(parameterAnnotations[i][0]));
                    if (parameterType.contains(ContractParameterType.ARGUMENT)) {
                        if (arg instanceof String) {
                            arguments.put(parameterName, (String) arg);
                        } else if (arg instanceof BigInteger) {
                            arguments.put(parameterName, (BigInteger) arg);
                        } else if (arg instanceof Long) {
                            arguments.put(parameterName, (Long) arg);
                        } else if (arg instanceof Integer) {
                            arguments.put(parameterName, (Integer) arg);
                        } else {
                            throw new NearException("Argument not supported in the ContractClient call");
                        }
                    }
                    if (parameterType.contains(ContractParameterType.PRIVATE_KEY)) {
                        privateKey = (PrivateKey) arg;
                    }
                    if (parameterType.contains(ContractParameterType.PUBLIC_KEY)) {
                        publicKey = (PublicKey) arg;
                    }
                    if (parameterType.contains(ContractParameterType.ACCOUNT_ID)) {
                        accountId = (String) arg;
                    }
                    if (parameterType.contains(ContractParameterType.DEPOSIT)) {
                        deposit = (BigInteger) arg;
                    }
                }
                if (methodType.equals(ContractMethodType.VIEW)) {
                    final Class<?> returnType = (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
                    return client.invoke(nearClient, contractAccountId, methodName, methodType,
                            Base64String.fromDecodedString(arguments.toString()), returnType);
                } else if (methodType.equals(ContractMethodType.CALL)) {
                    return client.invoke(nearClient, contractAccountId, methodName, methodType,
                            accountId, publicKey, privateKey, arguments, deposit);
                } else if (methodType.equals(ContractMethodType.CALL_ASYNC)) {
                    return client.invokeAsync(nearClient, contractAccountId, methodName, methodType,
                            accountId, publicKey, privateKey, arguments, deposit);
                } else {
                    throw new NearException("Unknown contract method type");
                }
            }
        });
    }
}
