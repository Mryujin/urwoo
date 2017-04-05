package com.urwoo.security.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class RolePo implements Serializable{

    private Integer id;
    private String name;
    private String roleCode;
}
