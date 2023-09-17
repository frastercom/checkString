package com.example.checkstring.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void test() {
        assertTrue(StringUtils.check("", ""));
        assertTrue(StringUtils.check("{hello ()((world)}{}", "{}"));
        assertFalse(StringUtils.check("{hello ()((world)}{", "{}"));

        assertTrue(StringUtils.check("{hello ()(()world)}{}", ""));
        assertTrue(StringUtils.check("{hello {()(()world)}}{}", ""));
        assertTrue(StringUtils.check("{hello ()({()}world)}{}", ""));
        assertTrue(StringUtils.check("{hello {()}(()world)}{}", ""));
        assertFalse(StringUtils.check("{hello ()()world)}{}", ""));
        assertFalse(StringUtils.check("hello )((world){}", ""));
        assertFalse(StringUtils.check("hello ()(()world)}{", ""));
        assertFalse(StringUtils.check(")))(((}}}{{{", ""));
        assertFalse(StringUtils.check("{(})", ""));
        assertFalse(StringUtils.check("{(}{)}", ""));
    }
}