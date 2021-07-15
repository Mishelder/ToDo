package com.miaskor.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertyUtil {

    public static String getProperty(String key,String nameProperty){
        var propertyFile = getPropertyFile(nameProperty);
        return propertyFile.getProperty(key);
    }

    public Properties getPropertyFile(String name){
        try (var stream = PropertyUtil.class.getClassLoader()
                .getResourceAsStream("%s.properties".formatted(name))) {
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
