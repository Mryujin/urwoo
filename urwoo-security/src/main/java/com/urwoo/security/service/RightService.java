package com.urwoo.security.service;

import com.urwoo.security.po.RightPo;
import com.urwoo.security.query.RightQueryParam;

import java.util.List;

public interface RightService {

    int saveRight(RightPo rightPo);

    void updateRight(RightPo rightPo);

    void deleteRight(Integer ...ids);

    RightPo getRight(Integer id);

    List<RightPo> rightList(RightQueryParam queryParam, int start, int pageSize);

    int rightCount(RightQueryParam queryParam);
}
