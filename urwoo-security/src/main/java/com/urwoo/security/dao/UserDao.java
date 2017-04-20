package com.urwoo.security.dao;

import com.urwoo.framework.jdbc.core.CommonDao;
import com.urwoo.security.po.UserPo;

public interface UserDao extends CommonDao<UserPo> {

    UserPo get(String username);
}
