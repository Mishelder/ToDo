package com.miaskor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyUtilTest {

    @Test
    void should_ReturnValue_ifPropertyIsExist() {
        //given
        String givenKey = "db.userName";
        String givenPropertyName = "database";
        String expected = "postgres";
        //when
        var actual = PropertyUtil.getProperty(givenKey, givenPropertyName);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void should_ReturnNull_ifPropertyIsNotExist() {
        //given
        String givenKey = "notExistKey";
        String givenPropertyName = "database";
        //when
        var actual = PropertyUtil.getProperty(givenKey, givenPropertyName);
        //then
        assertNull(actual);
    }
}