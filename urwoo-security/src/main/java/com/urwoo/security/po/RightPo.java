package com.urwoo.security.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class RightPo implements Serializable{

    private Integer id;
    private String name;
    private Integer type;
    private String url;
    private String rightCode;
}
