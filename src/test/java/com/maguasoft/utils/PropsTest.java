package com.maguasoft.utils;

import com.maguasoft.jdbc.Dialect;
import com.maguasoft.jdbc.support.ConfigProps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class PropsTest {

    @Test
    public void testGetProps() {
        Properties props = Props.getProps(Dialect.DEFAULT_PATH);
        Assert.assertNotNull(props);
        Assert.assertNotNull(props.get(Dialect.DATABASE_URI));
        Assert.assertNotNull(props.get(Dialect.DATABASE_NAME));
        Assert.assertNotNull(props.get(Dialect.DATABASE_PASSWORD));
        Assert.assertNotNull(props.get(Dialect.DATABASE_DIALECT));
        Assert.assertNotNull(props.get(Dialect.DATABASE_ENTITY_PACKAGE));
    }

    @Test
    public void testGetPropsValueBy() {
        String value = Props.getPropsValueBy(Dialect.DEFAULT_PATH, Dialect.DATABASE_ENTITY_PACKAGE);
        Assert.assertNotNull(value);
    }

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
