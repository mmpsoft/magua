package com.maguasoft.magua.commons.utils.bean;

import com.maguasoft.magua.commons.io.Conf;

@Conf(prefix = "database")
public class TestBean {
    private String uri;
    private String name;
    private String password;
    private String dialect;
    private String entityPackage;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    @Override
    public String toString() {
        return "ConfigProps{" +
                "uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dialect='" + dialect + '\'' +
                ", entityPackage='" + entityPackage + '\'' +
                '}';
    }
}
