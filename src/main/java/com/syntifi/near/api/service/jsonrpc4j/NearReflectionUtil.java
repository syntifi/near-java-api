package com.syntifi.near.api.service.jsonrpc4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcFixedParam;
import com.googlecode.jsonrpc4j.JsonRpcFixedParams;
import com.googlecode.jsonrpc4j.ReflectionUtil;

/**
 * Custom jsonrpc4j ReflectionUtils implementation to account for fixed
 * parameters
 * 
 * Temporary workaround
 * Pull request https://github.com/briandilley/jsonrpc4j/pull/282
 */
public class NearReflectionUtil extends ReflectionUtil {

    @SuppressWarnings("unchecked")
    public static Object parseArguments(Method method, Object[] arguments) {
        // Call "super"
        Object result = ReflectionUtil.parseArguments(method, arguments);

        JsonRpcFixedParams jsonRpcFixedParams = getAnnotation(method, JsonRpcFixedParams.class);
        // WARN: Will only catch first even if multiple annotations present
        JsonRpcFixedParam jsonRpcFixedParam = getAnnotation(method, JsonRpcFixedParam.class);

        if (jsonRpcFixedParams == null && jsonRpcFixedParam == null) {
            return result;
        }

        Map<String, Object> fixedParams = new LinkedHashMap<>();
        if (jsonRpcFixedParams != null) {
            for (JsonRpcFixedParam fixedParam : jsonRpcFixedParams.fixedParams()) {
                fixedParams.put(fixedParam.name(), fixedParam.value());
            }
        }
        if (jsonRpcFixedParam != null) {
            fixedParams.put(jsonRpcFixedParam.name(), jsonRpcFixedParam.value());
        }

        if (result instanceof Map) {
            ((Map<String, Object>) result).putAll(fixedParams);

            return result;
        } else if (result instanceof Object[]) {
            int currentIndex = ((Object[]) result).length;
            Object[] finalResult = Arrays.copyOf((Object[]) result, currentIndex + fixedParams.size());
            for (Map.Entry<String, Object> entry : fixedParams.entrySet()) {
                finalResult[currentIndex++] = entry.getValue();
            }

            return finalResult;
        } else {
            throw new RuntimeException("Invalid arguments object");
        }
    }
}
