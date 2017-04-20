package com.urwoo.security.service;

import com.urwoo.security.exception.UserNotExitException;
import com.urwoo.security.exception.UserUsernameExitException;
import com.urwoo.security.po.UserPo;
import com.urwoo.security.query.UserQueryParam;

import java.util.List;

public interface UserService {

    int saveUser(UserPo rolePo) throws UserUsernameExitException;

    void updateUser(UserPo rolePo) throws UserNotExitException, UserUsernameExitException;

    void deleteUser(Integer ...ids);

    void userGrantRole(Integer userId, Integer[] roleIds);

    UserPo getUser(Integer id);

    UserPo getUser(String username);

    List<UserPo> userList(UserQueryParam queryParam, int start, int pageSize);

    int userCount(UserQueryParam queryParam);
}
