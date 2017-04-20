package com.urwoo.security.dao;

import java.util.List;
import com.urwoo.framework.jdbc.core.CommonDao;
import com.urwoo.security.po.RolePo;

public interface RoleDao extends CommonDao<RolePo>{

    List<RolePo> userRoleList(Integer userId);

    RolePo get(String name);
}
