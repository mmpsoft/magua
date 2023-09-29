package com.maguasoft.magua.orm;

import com.maguasoft.magua.orm.support.BeanPropertyMapper;
import com.maguasoft.magua.utils.NameGenerator;
import com.maguasoft.magua.utils.Props;
import com.maguasoft.magua.utils.Strings;

import java.sql.ResultSet;
import java.util.Objects;

public interface RowMapper<T> {

    /**
     * 策略默认的RowMapper
     *
     * @param resultSet
     * @return
     */
    RowMapper<?> DEFAULT_MAPPER = new RowMapper<Object>() {
        RowMapper<?> beanMapper;

        @Override
        public Object mapRow(ResultSet resultSet) throws Exception {
            if (Objects.isNull(beanMapper)) {
                this.beanMapper = getDefaultMapper(resultSet);
            }

            return this.beanMapper.mapRow(resultSet);
        }
    };

    /**
     * ORM
     *
     * @param resultSet
     * @return
     */
    T mapRow(ResultSet resultSet) throws Exception;

    /**
     * 默认的row mapper
     *
     * @param resultSet
     * @return
     * @throws Exception
     */
    default RowMapper<?> getDefaultMapper(ResultSet resultSet) throws Exception {
        String className = NameGenerator.capitalize(resultSet.getMetaData().getTableName(1));
        String entityPackage = Props.getPropsValueBy(Dialect.DEFAULT_PATH, Dialect.DATABASE_ENTITY_PACKAGE);
        if (Strings.isBlank(className) || Strings.isBlank(entityPackage)) {
            throw new RuntimeException("entityPackage cannot be empty.");
        }

        String ref = String.format("%s.%s", entityPackage, className);
        try {
            return new BeanPropertyMapper<>(Class.forName(ref));
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(ref + " Class not found, Check the <entity-package> config is correct", e);
        }
    }
}
