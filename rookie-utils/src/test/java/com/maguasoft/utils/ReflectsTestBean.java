package com.maguasoft.utils;

import com.maguasoft.utils.bean.UserBean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ReflectsTestBean {

    @Test
    public void testGetMethods() {
        Map<String, Method> methods = Reflects.getMethods(UserBean.class, m -> Strings.startsWith(m, Reflects.BEAN_METHOD_PREFIX));
        Assert.assertFalse(methods.isEmpty());
    }

    @Test
    public void testGetBeanMethods() {
        Map<String, Method> methods = Reflects.getBeanMethods(UserBean.class);
        Assert.assertFalse(methods.isEmpty());
    }
}