package com.miaskor.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WebFilePath {

    private static final String URL = "WEB-INF/%s/%s.%s";

    public static String getPath(String fileName, String extension, String folders) {
        return URL.formatted(folders, fileName, extension);
    }
}
