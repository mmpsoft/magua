package com.maguasoft.jdbc.support.entity;

import com.maguasoft.jdbc.Dialect;
import com.maguasoft.jdbc.support.ConfigProps;
import com.maguasoft.utils.Props;
import org.junit.Assert;
import org.junit.Test;

public class PropsTest {

    @Test
    public void testGetBeanProps() {
        ConfigProps beanProps = Props.getBeanProps(Dialect.DEFAULT_PATH, ConfigProps.class, Dialect.PREFIX);
        System.out.println(beanProps);
        Assert.assertNotNull(beanProps);
        Assert.assertNotNull(beanProps.getUri());
        Assert.assertNotNull(beanProps.getName());
        Assert.assertNotNull(beanProps.getPassword());
        Assert.assertNotNull(beanProps.getDialect());
        Assert.assertNotNull(beanProps.getEntityPackage());
    }
}
