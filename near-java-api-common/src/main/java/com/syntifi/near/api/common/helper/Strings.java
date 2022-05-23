package com.syntifi.near.api.common.helper;

import java.net.URL;

/**
 * Helper methods for Strings
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.2.0
 */
public class Strings {
    public static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
