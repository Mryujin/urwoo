package com.urwoo.framework.plugins.memcache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MamcacheConfiguration {

    @Value("#{'${memcache.server.list}'.split(',')}")
    private List<String> serverList;

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }
}
