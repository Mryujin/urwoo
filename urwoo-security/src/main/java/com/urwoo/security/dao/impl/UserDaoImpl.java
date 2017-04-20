package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.EntityCreator;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.UserDao;
import com.urwoo.security.po.UserPo;
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
public class UserDaoImpl extends SpringJdbcDao implements UserDao, TableConst{

    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(UserPo userPo) {

        String sql = "insert into (name, username, password) "+ TABLE_USER +"values (?,?,?)";
        logger.info("===========>sql= {},params={}",sql,
                String.format("name=%s, username=%s, password=%s",userPo.getName(), userPo.getUsername(), userPo.getPassword()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new EntityCreator(sql, new Object[]{
                userPo.getName(), userPo.getUsername(), userPo.getPassword()
        }), keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void update(UserPo userPo) {

        String sql = "update "+ TABLE_USER +" set name=?, username=?, password=? where id=?";
        logger.info("===========>sql= {},params={}",sql,
                String.format("name=%s, username=%s, password=%s, id=%d",userPo.getName(), userPo.getUsername(), userPo.getPassword(), userPo.getId()));

        jdbcTemplate.update(sql, new Object[]{userPo.getName(), userPo.getUsername(), userPo.getPassword(), userPo.getId()});
    }

    public void delete(Integer... ids) {

        String sql = "delete from "+ TABLE_USER + " where id in (?)";
        logger.info("===========>sql= {},params={}",sql, ids);

        jdbcTemplate.update(sql, new Object[]{ids});
    }

    public UserPo get(Integer id) {
        String sql = "select id,name,username from "+ TABLE_USER +" where id=:id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return get(sql, UserPo.class, param);
    }

    public UserPo get(String username) {
        String sql = "select id,name,username from "+ TABLE_USER +" where username=:username";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", username);
        return get(sql, UserPo.class, param);
    }

    public List<UserPo> list(Map<String, Object> queryParam, int start, int pageSize) {
        logger.info("list() : queryParam={}, start={}, pageSize={}", queryParam, start, pageSize);
        String sql = "select * from "+TABLE_USER;
        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);
        logger.info("===========>sql={}", stringBuilder.toString());
        return list(stringBuilder.toString() ,UserPo.class, start, pageSize, queryParam);
    }

    public int count(Map<String, Object> queryParam) {
        String sql = "select count(1) from "+TABLE_USER;
        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);
        logger.info(">>>>>>>>>>sql={}", stringBuilder.toString());
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
