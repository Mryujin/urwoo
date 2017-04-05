package com.urwoo.framework.jdbc.core;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.*;

/**
 * 实体创建类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2017-01-02-1:33
 */
public class EntityCreator implements PreparedStatementCreator {

    private String sql;
    private Object[] params;

    public EntityCreator(String sql, Object[] params){
        this.sql = sql;
        this.params = params;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ParameterMetaData parameterMetaData = ps.getParameterMetaData();
        int paramCount = parameterMetaData.getParameterCount();
        for (int i=0; i < paramCount; i++){
            ps.setObject(i+1, params[i]);
        }
        return ps;
    }
}
