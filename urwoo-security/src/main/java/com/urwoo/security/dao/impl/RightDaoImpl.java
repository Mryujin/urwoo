package com.urwoo.security.dao.impl;

import com.urwoo.common.consants.TableConst;
import com.urwoo.framework.jdbc.core.SpringJdbcDao;
import com.urwoo.security.dao.RightDao;
import com.urwoo.security.po.RightPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RightDaoImpl extends SpringJdbcDao implements RightDao, TableConst{

    private Logger logger = LoggerFactory.getLogger(RightDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(RightPo rightPo) {
        String sql = "insert into "+ TABLE_ROLE +" (name,role_code) value(?,?,?)";
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
