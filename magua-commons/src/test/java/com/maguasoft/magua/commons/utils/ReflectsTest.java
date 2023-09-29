package com.maguasoft.magua.commons.utils;

import com.maguasoft.magua.commons.reflect.MethodDescriptor;
import com.maguasoft.magua.commons.reflect.Reflects;
import com.maguasoft.magua.commons.text.Strings;
import com.maguasoft.magua.commons.utils.bean.UserBean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ReflectsTest {

    @Test
    public void testGetMethods1() {
        Map<String, Method> methods = Reflects.getMethods(UserBean.class, m -> Strings.startsWith(m, Reflects.BEAN_METHOD_PREFIX));
        Assert.assertFalse(methods.isEmpty());
    }

    @Test
    public void testGetMethods2() {
        Map<String, ?> methods = Reflects.getMethods(UserBean.class, m -> Strings.startsWith(m, Reflects.BEAN_METHOD_PREFIX), BeanMethodDescriptor::new);

        Assert.assertFalse(methods.isEmpty());
        boolean isSetter = methods.keySet().stream().anyMatch(m -> Strings.startsWith(m, Reflects.SETTER_METHOD_PREFIX));
        boolean isGetter = methods.keySet().stream().anyMatch(m -> Strings.startsWith(m, Reflects.GETTER_METHOD_PREFIX));
        Assert.assertTrue(isSetter);
        Assert.assertTrue(isGetter);
    }

    private static class BeanMethodDescriptor implements MethodDescriptor {
        private final Method method;

        public BeanMethodDescriptor(Method method) {
            this.method = method;
        }

        @Override
        public String getName() {
            return method.getName();
        }

        @Override
        public Method getMethod() {
            return method;
        }
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