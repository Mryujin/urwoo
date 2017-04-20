package com.urwoo.security.query;

import com.urwoo.security.query.order.OrderParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserQueryParam {
    private String name;
    private OrderParam orderParam;
}
