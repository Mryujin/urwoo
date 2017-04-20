package com.urwoo.security.service;

import java.util.List;
import com.urwoo.security.po.RolePo;
import com.urwoo.security.query.RoleQueryParam;

public interface RoleService {

    int saveRole(RolePo rolePo);

    void updateRole(RolePo rolePo);

    void deleteRole(Integer ...ids);

    void roleGrantRight(Integer roleId, Integer[] rightIds);

    RolePo getRole(Integer id);

    RolePo getRole(String name);

    List<RolePo> userRoleList(Integer userId);

    List<RolePo> roleList(RoleQueryParam queryParam, int start, int pageSize);

    int roleCount(RoleQueryParam queryParam);
}
