package com.urwoo.security.web.controller;

import com.urwoo.framework.web.response.UrwooResponse;
import com.urwoo.framework.web.response.UrwooResponses;
import com.urwoo.security.dao.UserDao;
import com.urwoo.security.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;

    @PostMapping(name = "login", produces = {})
    public UrwooResponse login(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password){

        UserPo userPo = userDao.get(username);
        return UrwooResponses.success(userPo);

    }

    @GetMapping(name = "get", produces = {})
    public UrwooResponse get(){
        return UrwooResponses.success();
    }
}
