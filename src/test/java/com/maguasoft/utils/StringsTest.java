package com.maguasoft.utils;

import com.maguasoft.jdbc.RowMapper;
import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(Strings.isEmpty(null));
        Assert.assertTrue(Strings.isEmpty(""));
        Assert.assertFalse(Strings.isEmpty(" "));
        Assert.assertFalse(Strings.isEmpty("FFF"));
        Assert.assertFalse(Strings.isEmpty(" FFF"));
        Assert.assertFalse(Strings.isEmpty("FFF "));
        Assert.assertFalse(Strings.isEmpty(" FFF "));
        Assert.assertFalse(Strings.isEmpty("FF F"));
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(Strings.isBlank(null));
        Assert.assertTrue(Strings.isBlank(""));
        Assert.assertTrue(Strings.isBlank(" "));
        Assert.assertFalse(Strings.isBlank("FFF"));
        Assert.assertFalse(Strings.isBlank(" FFF"));
        Assert.assertFalse(Strings.isBlank("FFF "));
        Assert.assertFalse(Strings.isBlank(" FFF "));
        Assert.assertFalse(Strings.isBlank("FF F"));
    }

    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(Strings.isNotBlank(null));
        Assert.assertFalse(Strings.isNotBlank(""));
        Assert.assertFalse(Strings.isNotBlank(" "));
        Assert.assertTrue(Strings.isNotBlank("FFF"));
        Assert.assertTrue(Strings.isNotBlank(" FFF"));
        Assert.assertTrue(Strings.isNotBlank("FFF "));
        Assert.assertTrue(Strings.isNotBlank(" FFF "));
        Assert.assertTrue(Strings.isNotBlank("FF F"));
    }

    @Test
    public void testStartsWith() {
        Assert.assertFalse(Strings.startsWith("", null));
        Assert.assertTrue(Strings.startsWith("setter", "set"));
        Assert.assertTrue(Strings.startsWith("getter", "get"));
        Assert.assertTrue(Strings.startsWith("setter", Reflects.BEAN_METHOD_PREFIX));
        Assert.assertTrue(Strings.startsWith("getter", Reflects.BEAN_METHOD_PREFIX));
        Assert.assertTrue(Strings.startsWith("method", "test|set|get|method"));
    }

    @Test
    public void testContains() {
        Assert.assertTrue(Strings.contains("setter", "set"));
        Assert.assertTrue(Strings.contains("getter", "get"));
        Assert.assertTrue(Strings.contains("setter", Reflects.BEAN_METHOD_PREFIX));
        Assert.assertTrue(Strings.contains("getter", Reflects.BEAN_METHOD_PREFIX));
        Assert.assertTrue(Strings.contains("method", "test|set|get|method"));
    }

    @Test
    public void testContainsAll() {
        Assert.assertFalse(Strings.containsAll(""));
        Assert.assertTrue(Strings.containsAll("setter", "set"));
        Assert.assertTrue(Strings.containsAll("getter", "get"));
        Assert.assertTrue(Strings.containsAll("setter", "set", "get"));
        Assert.assertTrue(Strings.containsAll("getter", "set", "get"));
        Assert.assertTrue(Strings.containsAll("method", "test", "method"));
    }

    @Test
    public void testSplit() {
        Assert.assertTrue(Strings.split("column_Name", RowMapper.COLUMN_NAME_SPLIT_CHAR).length > 1);
        Assert.assertTrue(Strings.split("column-Name", RowMapper.COLUMN_NAME_SPLIT_CHAR).length > 1);
    }
}