package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.RoleDao;
import com.urwoo.security.po.RolePo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class RoleDaoImpl extends SpringJdbcDao implements RoleDao, TableConst{

    public int save(RolePo rolePo) {
        return 0;
    }

    public void update(RolePo rolePo) {

    }

    public void delete(Integer... ids) {

    }

    public RolePo get(Integer id) {
        return null;
    }

    public List<RolePo> list(Map<String, Object> queryParam, int start, int pageSize) {
        return null;
    }

    public int count(Map<String, Object> queryParam) {
        return 0;
    }
}
