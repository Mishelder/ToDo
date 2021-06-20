package com.miaskor.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertyUtil {

    public static String getProperty(String key,String nameProperty){
        try (var stream = PropertyUtil.class.getClassLoader()
                .getResourceAsStream("%s.properties".formatted(nameProperty))) {
            Properties properties = new Properties();
            properties.load(stream);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
