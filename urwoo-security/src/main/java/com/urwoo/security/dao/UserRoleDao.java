package com.urwoo.security.dao;

import com.urwoo.security.po.UserRolePo;

public interface UserRoleDao {

    void save(UserRolePo userRolePo);

    void delete(Integer userId, Integer roleId);
}
