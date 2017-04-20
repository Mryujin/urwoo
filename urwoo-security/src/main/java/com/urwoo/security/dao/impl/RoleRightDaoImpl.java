package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.security.dao.RoleRightDao;
import com.urwoo.security.po.RoleRightPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRightDaoImpl implements RoleRightDao, TableConst{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(RoleRightPo roleRightPo) {
        String sql = "insert into "+TABLE_ROLE_RIGHT +" (role_id, right_id) value(?,?)";
        jdbcTemplate.update(sql, new Object[]{roleRightPo.getRoleId(), roleRightPo.getRightId()});
    }

    public void delete(Integer roleId, Integer rightId) {
        String sql = "delete from "+TABLE_ROLE_RIGHT +" where role_id=? and right_id=?";
        jdbcTemplate.update(sql, new Object[]{roleId, rightId});
    }
}
