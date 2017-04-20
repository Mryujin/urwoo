package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.EntityCreator;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.RoleDao;
import com.urwoo.security.po.RolePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class RoleDaoImpl extends SpringJdbcDao implements RoleDao, TableConst{

    private Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(RolePo rolePo) {

        String sql = "insert into "+ TABLE_ROLE +" (name,role_code) value(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new EntityCreator(sql, new Object[]{
                rolePo.getName(), rolePo.getRoleCode()
        }), keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void update(RolePo rolePo) {

        String sql = "update "+ TABLE_ROLE +" set name=?, role_code=? where id=?";
        jdbcTemplate.update(sql, new Object[]{rolePo.getName(), rolePo.getRoleCode(), rolePo.getId()});
    }

    public void delete(Integer... ids) {
        String sql = "delete from "+ TABLE_ROLE+" where id in (?)";
        jdbcTemplate.update(sql, new Object[]{ids});
    }

    public RolePo get(Integer id) {
        String sql = "select id,name,role_code from "+ TABLE_ROLE+ " where id=:id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return get(sql, RolePo.class, param);
    }

    public RolePo get(String name) {
        String sql = "select id,name,role_code from "+ TABLE_ROLE+ " where name=:name";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", name);
        return get(sql, RolePo.class, param);
    }

    public List<RolePo> userRoleList(Integer userId) {
        String sql = "select r.id,r.name,r.role_code from "+ TABLE_ROLE+" r left join "+ TABLE_USER_ROLE +" ur on r.id=ur.role_id where ur.user_id=:userId";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userId", userId);
        return list(sql, RolePo.class, param);
    }

    public List<RolePo> list(Map<String, Object> queryParam, int start, int pageSize) {
        String sql = "select id,name,role_code from "+TABLE_ROLE;
        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);
        return list(stringBuilder.toString() ,RolePo.class, start, pageSize, queryParam);
    }

    public int count(Map<String, Object> queryParam) {
        String sql = "select count(1) from "+TABLE_ROLE;
        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);
        return count(stringBuilder.toString(), queryParam);
    }

    private void genConditionSql(Map<String,Object> queryParam, StringBuilder SQL){
        SQL.append(" WHERE 1=1");
//        if (queryParam.containsKey("startDate") && queryParam.containsKey("endDate")){
//            SQL.append(" AND create_time BETWEEN :startDate AND :endDate");
//            queryParam.put("startDate", queryParam.get("startDate"));
//            queryParam.put("endDate", queryParam.get("endDate"));
//        }

        if (queryParam.containsKey("name")){
            SQL.append(" AND name LIKE CONCAT('%',CONCAT(:name,'%'))");
            queryParam.put("name", queryParam.get("name"));
        }
    }
}