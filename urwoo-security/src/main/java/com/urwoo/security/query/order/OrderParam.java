package com.urwoo.security.query.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderParam {

    private String propertyName;

    private boolean isAsc;
}
