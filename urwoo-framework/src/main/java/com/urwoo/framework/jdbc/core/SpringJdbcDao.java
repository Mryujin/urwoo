package com.urwoo.framework.jdbc.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 封装spring-jdbc的查询方法。
 * 这个类的规范：
 * （1）数据库表中的属性首字母小写，多个单词组成的属性名称用“_”连接
 * （2）对应的对象的成员变量用驼峰是命名。
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2017-01-01-14:50
 */
public class SpringJdbcDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public <T> T get(final String sql, final Class<T> clazz, Map<String,Object> params){
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new QueryMapper<T>(clazz));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public <T> List<T> list(final String sql, final Class<T> clazz, Map<String,Object> params){
        return list(sql, clazz, -1, -1, params);
    }

    public <T> List<T> list(final String sql, final Class<T> clazz, final int start, final int limit, Map<String,Object> params){
        StringBuilder strBuilder = new StringBuilder(sql);
        if (start > -1 && limit > -1){
            strBuilder.append(" LIMIT :start, :limit");
            params.put("start", start);
            params.put("limit", limit);
        }
        return namedParameterJdbcTemplate.query(strBuilder.toString(), params, new QueryMapper<T>(clazz));
    }

    public int count(final String sql, Map<String,Object> params){
        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }
}