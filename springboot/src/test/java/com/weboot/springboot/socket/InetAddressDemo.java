package com.weboot.springboot.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("FM57V51O6SKI7XR");
        String hostName = inetAddress.getHostName();
        String hostAddress = inetAddress.getHostAddress();
        System.out.println(hostName+hostAddress);
    }
}
