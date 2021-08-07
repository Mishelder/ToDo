package com.miaskor.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGeneratorImpl implements CodeGenerator{
    private static final CodeGeneratorImpl INSTANCE = new CodeGeneratorImpl();

    @Override
    public String generate() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9000)+1000);
    }

    public static CodeGeneratorImpl getInstance(){
        return INSTANCE;
    }
}
