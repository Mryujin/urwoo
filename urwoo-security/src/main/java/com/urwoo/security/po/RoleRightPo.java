package com.urwoo.security.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleRightPo {

    private Integer id;
    private Integer roleId;
    private Integer rightId;
}