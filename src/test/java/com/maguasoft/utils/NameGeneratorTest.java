package com.maguasoft.utils;

import org.junit.Assert;
import org.junit.Test;

public class NameGeneratorTest {

    @Test
    public void testCapitalize() {
        Assert.assertEquals(NameGenerator.capitalize("column_Name"), "ColumnName");
        Assert.assertEquals(NameGenerator.capitalize("column_name"), "ColumnName");
        Assert.assertEquals(NameGenerator.capitalize("column-name"), "ColumnName");
        Assert.assertEquals(NameGenerator.capitalize("columnname"), "Columnname");
    }

    @Test
    public void testGetSetterMethodName() {
        Assert.assertEquals(NameGenerator.getSetterMethodName("column_Name"), "setColumnName");
        Assert.assertEquals(NameGenerator.getSetterMethodName("column_name"), "setColumnName");
        Assert.assertEquals(NameGenerator.getSetterMethodName("column-name"), "setColumnName");
        Assert.assertEquals(NameGenerator.getSetterMethodName("columnname"), "setColumnname");
    }
}
