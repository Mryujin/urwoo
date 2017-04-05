package com.urwoo.security.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserPo implements Serializable{

    private Integer id;
    private String name;
    private String username;
    private String password;
}
