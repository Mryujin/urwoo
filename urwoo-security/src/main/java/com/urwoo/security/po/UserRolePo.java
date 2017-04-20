package com.urwoo.security.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRolePo {

    private Integer id;
    private Integer userId;
    private Integer roleId;
}
