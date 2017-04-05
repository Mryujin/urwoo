package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.RightDao;
import com.urwoo.security.po.RightPo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RightDaoImpl extends SpringJdbcDao implements RightDao, TableConst{

    public int save(RightPo rightPo) {
        return 0;
    }

    public void update(RightPo rightPo) {

    }

    public void delete(Integer... ids) {

    }

    public RightPo get(Integer id) {
        return null;
    }

    public List<RightPo> list(Map<String, Object> queryParam, int start, int pageSize) {
        return null;
    }

    public int count(Map<String, Object> queryParam) {
        return 0;
    }
}
