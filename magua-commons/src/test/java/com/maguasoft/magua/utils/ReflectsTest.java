package com.maguasoft.magua.utils;

import com.maguasoft.magua.utils.bean.UserBean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ReflectsTest {

    @Test
    public void testGetMethods() {
        Map<String, Method> methods = Reflects.getMethods(UserBean.class, m -> Strings.startsWith(m, Reflects.BEAN_METHOD_PREFIX));
        Assert.assertFalse(methods.isEmpty());
    }

    @Test
    public void testGetBeanMethods() {
        Map<String, Method> methods = Reflects.getBeanMethods(UserBean.class);
        Assert.assertFalse(methods.isEmpty());
        boolean isSetter = methods.keySet().stream().anyMatch(m -> Strings.startsWith(m, Reflects.SETTER_METHOD_PREFIX));
        boolean isGetter = methods.keySet().stream().anyMatch(m -> Strings.startsWith(m, Reflects.GETTER_METHOD_PREFIX));
        Assert.assertTrue(isSetter);
        Assert.assertTrue(isGetter);
    }
}