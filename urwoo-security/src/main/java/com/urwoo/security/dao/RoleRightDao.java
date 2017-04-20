package com.urwoo.security.dao;

import com.urwoo.security.po.RoleRightPo;

public interface RoleRightDao {

    void save(RoleRightPo roleRightPo);

    void delete(Integer roleId, Integer rightId);
}
