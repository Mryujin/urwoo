package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.EntityCreator;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.RightDao;
import com.urwoo.security.po.RightPo;
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
public class RightDaoImpl extends SpringJdbcDao implements RightDao, TableConst{

    private Logger logger = LoggerFactory.getLogger(RightDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(RightPo rightPo) {

        String sql = "insert into "+ TABLE_RIFHT +" (name, type, url, right_code, parent_id) value(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new EntityCreator(sql, new Object[]{
                rightPo.getName(), rightPo.getType(), rightPo.getUrl(), rightPo.getRightCode(), rightPo.getParentId()
        }), keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void update(RightPo rightPo) {
        String sql = "update "+ TABLE_RIFHT +" set name=?, type=?, url=?, right_code=?, parent_id=? where id=?";
        jdbcTemplate.update(sql, new Object[]{
                rightPo.getName(), rightPo.getType(), rightPo.getUrl(), rightPo.getRightCode(),
                rightPo.getParentId(), rightPo.getId()
        });
    }

    public void delete(Integer... ids) {
        String sql = "delete from "+ TABLE_RIFHT+ " where id in (?)";
        jdbcTemplate.update(sql, new Object[]{ids});
    }

    public RightPo get(Integer id) {

        String sql = "select id, name, type, url, right_code, parent_id where id=:id";

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("id", id);

        return get(sql, RightPo.class, param);
    }

    public List<RightPo> roleRightList(Integer roleId){
        String sql = "select r.* from "+ TABLE_RIFHT +" r left join "+ TABLE_ROLE_RIGHT +" rr on r.id=rr.right_id where rr.role_id=:roleId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        return list(sql, RightPo.class, params);
    }

    public List<RightPo> userRightList(Integer userId){
        String sql = "select r.* from "+ TABLE_RIFHT +" r left join "+ TABLE_ROLE_RIGHT +" rr on r.id=rr.right_id " +
                     "left join "+ TABLE_USER_ROLE +" ur on ur.role_id=rr.role_id where ur.user_id=:userId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", userId);
        return list(sql, RightPo.class, params);
    }

    public List<RightPo> list(Map<String, Object> queryParam, int start, int pageSize) {
        String sql = "select id, name, type, url, right_code, parent_id from "+ TABLE_RIFHT;
        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);
        return list(sql, RightPo.class, start, pageSize,queryParam);
    }

    public int count(Map<String, Object> queryParam) {
        String sql = "select count(1) from "+ TABLE_RIFHT;

        StringBuilder stringBuilder = new StringBuilder(sql);
        genConditionSql(queryParam, stringBuilder);

        return count(sql, queryParam);
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