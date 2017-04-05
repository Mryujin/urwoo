package com.urwoo.framework.jdbc.core;

import java.util.List;
import java.util.Map;

public interface CommonDao<T> {

    int save(T t);

    void update(T t);

    void delete(Integer ...ids);

    T get(Integer id);

    List<T> list(Map<String,Object> queryParam, int start, int pageSize);

    int count(Map<String,Object> queryParam);
}
