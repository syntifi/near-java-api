package com.syntifi.near.api.service.jsonrpc4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.ReflectionUtil;

public class NearReflectionUtil extends ReflectionUtil {

    @SuppressWarnings("unchecked")
    public static Object parseArguments(Method method, Object[] arguments) {
        // Call "super"
        Object result = ReflectionUtil.parseArguments(method, arguments);

        JsonRpcFixedStringParam fixedStringParams = getAnnotation(method, JsonRpcFixedStringParam.class);

        if (fixedStringParams == null || result == null) {
            return result;
        }

        if (result instanceof Map) {
            if (fixedStringParams.name().length != fixedStringParams.value().length) {
                throw new RuntimeException("Different name/value counts");
            }
            for (int i = 0; i < fixedStringParams.name().length; i++) {
                ((HashMap<String, String>) result).put(fixedStringParams.name()[i], fixedStringParams.value()[i]);
            }

            return result;
        } else if (result instanceof Object[]) {
            Object[] finalResult = Arrays.copyOf((Object[]) result, ((Object[]) result).length + 1);
            finalResult[((Object[]) result).length] = fixedStringParams.value();
            return finalResult;
        } else {
            throw new RuntimeException("Invalid arguments object");
        }
    }
}
