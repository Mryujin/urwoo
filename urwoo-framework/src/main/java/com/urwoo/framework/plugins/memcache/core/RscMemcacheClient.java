package com.urwoo.framework.plugins.memcache.core;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component
public class RscMemcacheClient {

    @Autowired
    private MemcachedClient memcachedClient;

    public final boolean set(final String key, final int exp, final Object value){
        try {
            return memcachedClient.set(key, exp, value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public <T> T get(final String key, final long timeout) {
        try {
            return memcachedClient.get(key, timeout);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
