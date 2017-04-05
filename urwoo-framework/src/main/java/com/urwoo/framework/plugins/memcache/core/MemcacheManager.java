package com.urwoo.framework.plugins.memcache.core;

import com.urwoo.framework.lang.StringUtils;
import com.urwoo.framework.plugins.memcache.MamcacheConfiguration;
import net.rubyeye.xmemcached.HashAlgorithm;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemcacheManager {

    @Autowired
    private MamcacheConfiguration mamcacheConfiguration;

    @Bean
    public MemcachedClient build() throws IOException {

        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(
                getInetSocketAddressList(mamcacheConfiguration.getServerList()));
        //TODO write property file
        memcachedClientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator(HashAlgorithm.FNV1_32_HASH));
        return memcachedClientBuilder.build();
    }

    private List<InetSocketAddress> getInetSocketAddressList(List<String> serverList) {
        List<InetSocketAddress> inetSocketAddresses = new ArrayList<>();
        InetSocketAddress inetSocketAddress = null;
        for (String ipAdress : serverList){
            InterAddress interAddress = resolveAddress(ipAdress);
            try {
                inetSocketAddress = new InetSocketAddress(InetAddress.getByName(interAddress.getHost()), interAddress.getPort());
            } catch (UnknownHostException e) {
               throw new RuntimeException("Host unknown exception！");
            }
            inetSocketAddresses.add(inetSocketAddress);
        }
        return inetSocketAddresses;
    }

    /**
     * resolve ip:port
     */
    private InterAddress resolveAddress(String address){
        if (StringUtils.isEmpty(address)){
            throw new IllegalArgumentException("address is null!");
        }
        InterAddress interAddress = new InterAddress();
        String[] addresses = address.split(":");
        if (addresses.length == 2){
            interAddress.setHost(addresses[0]);
            interAddress.setPort(checkPort(addresses[1]));
        }else{
            interAddress.setHost(addresses[0]);
            interAddress.setPort(checkPort("80"));
        }
        return interAddress;
    }

    /**
     * check port
     */
    private int checkPort(String port) {
        if(!StringUtils.isNumber(port)){
            throw new IllegalArgumentException("port is not number:" + port);
        }
        int var0 = Integer.valueOf(port);
        if(var0 >= 0 && var0 <= '\uffff') {
            return var0;
        } else {
            throw new IllegalArgumentException("port out of range:" + var0);
        }
    }

    class InterAddress{
        private Integer port;
        private String host;

        public InterAddress(){}

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }
    }

    public static void main(String[] args) throws UnknownHostException {

    }
}