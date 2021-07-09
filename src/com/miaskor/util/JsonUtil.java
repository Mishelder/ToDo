package com.miaskor.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@UtilityClass
public class JsonUtil {

    private static final int BUFFER_SIZE = 256;

    public static String parseBody(HttpServletRequest req) {
        StringBuilder stringBuilder = new StringBuilder();
        try(InputStream inputStream = req.getInputStream()) {
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
