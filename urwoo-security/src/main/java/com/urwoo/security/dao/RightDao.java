package com.urwoo.security.dao;

import com.urwoo.framework.jdbc.core.CommonDao;
import com.urwoo.security.po.RightPo;

import java.util.List;

public interface RightDao extends CommonDao<RightPo>{

    List<RightPo> roleRightList(Integer roleId);

    List<RightPo> userRightList(Integer userId);
}
