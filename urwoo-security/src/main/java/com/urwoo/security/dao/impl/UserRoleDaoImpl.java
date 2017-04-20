package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.security.dao.UserRoleDao;
import com.urwoo.security.po.UserRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl implements UserRoleDao, TableConst{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(UserRolePo userRolePo) {
        String sql = "insert into "+ TABLE_USER_ROLE +"(user_id,role_id) value(?,?)";
        jdbcTemplate.update(sql, new Object[]{userRolePo.getUserId(), userRolePo.getRoleId()});
    }

    public void delete(Integer userId, Integer roleId) {
        String sql = "delete from "+ TABLE_USER_ROLE +" where user_id=? and role_id=?";
        jdbcTemplate.update(sql, new Object[]{userId, roleId});
    }
}