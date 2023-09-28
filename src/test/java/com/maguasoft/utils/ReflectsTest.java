package com.maguasoft.utils;

import com.maguasoft.jdbc.support.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ReflectsTest {

    @Test
    public void testGetMethods() {
        Map<String, Method> methods = Reflects.getMethods(User.class, m -> Strings.startsWith(m, Reflects.BEAN_METHOD_PREFIX));
        Assert.assertFalse(methods.isEmpty());
    }

    @Test
    public void testGetBeanMethods() {
        Map<String, Method> methods = Reflects.getBeanMethods(User.class);
        Assert.assertFalse(methods.isEmpty());
    }
}