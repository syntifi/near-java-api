package com.syntifi.near.api.service.jsonrpc4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.IJsonRpcClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

/**
 * Custom jsonrpc4j ProxyUtil implementation to account for fixed parameters
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearProxyUtil extends ProxyUtil {

    private static boolean isDeclaringClassAnObject(Method method) {
        return method.getDeclaringClass() == Object.class;
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

    public static <T> T createClientProxy(ClassLoader classLoader, Class<T> proxyInterface,
            final IJsonRpcClient client) {
        return createClientProxy(classLoader, proxyInterface, client, new HashMap<String, String>());
    }

    @SuppressWarnings("unchecked")
    private static <T> T createClientProxy(ClassLoader classLoader, Class<T> proxyInterface,
            final IJsonRpcClient client, final Map<String, String> extraHeaders) {

        return (T) Proxy.newProxyInstance(classLoader, new Class<?>[] { proxyInterface }, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (isDeclaringClassAnObject(method))
                    return proxyObjectMethods(method, proxy, args);

                final Object arguments = NearReflectionUtil.parseArguments(method, args);
                final String methodName = getMethodName(method);
                return client.invoke(methodName, arguments, method.getGenericReturnType(), extraHeaders);
            }
        });
    }
}
