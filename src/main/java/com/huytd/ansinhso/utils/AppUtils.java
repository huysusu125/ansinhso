package com.huytd.ansinhso.utils;

public class AppUtils {
    public static String getLastNineCharacters(String input) {
        if (input == null || input.length() <= 9) {
            return input;
        }
        return input.substring(input.length() - 9);
    }
}
