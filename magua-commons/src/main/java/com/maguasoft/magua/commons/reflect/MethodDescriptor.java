package com.maguasoft.magua.commons.reflect;

import java.lang.reflect.Method;

/**
 * 方法描述器
 */
public interface MethodDescriptor {
    /**
     * 方法名
     *
     * @return
     */
    String getName();

    /**
     * 获取方法
     *
     * @return
     */
    Method getMethod();
}
