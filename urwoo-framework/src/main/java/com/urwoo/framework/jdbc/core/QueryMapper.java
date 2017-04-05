package com.urwoo.framework.jdbc.core;

import com.urwoo.framework.jdbc.exception.SQLColmunConvertPropertyFailException;
import com.urwoo.framework.jdbc.utils.CamelCaseUtils;
import com.urwoo.framework.jdbc.utils.Reflects;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class QueryMapper<T> implements RowMapper<T> {

    private Class<T> clazz;

    public QueryMapper(Class<T> clazz){
        this.clazz = clazz;
    }

    public T mapRow(ResultSet rs, int i) throws SQLException {
        return genEntity(rs, clazz);
    }

    private <T> T genEntity(final ResultSet rs, final Class<T> clazz){
        try {
            T entity = clazz.newInstance();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            for (int i=0; i < columnCount; i++){
                //获取as后面的名称
                String columnName = rsMetaData.getColumnLabel(i+1);
                Object obj = rs.getObject(columnName);
                Reflects.setFieldValue(entity, CamelCaseUtils.toCamelCase(columnName), obj);
            }
            return entity;
        } catch (SQLException e) {
            throw new SQLColmunConvertPropertyFailException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
