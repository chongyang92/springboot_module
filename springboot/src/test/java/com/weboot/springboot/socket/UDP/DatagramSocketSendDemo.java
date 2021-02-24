package com.weboot.springboot.socket.UDP;

import java.io.IOException;
import java.net.*;

public class DatagramSocketSendDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] bytes = "hello,UDP我来了".getBytes();
        /*int len = bytes.length;
        InetAddress inetAddress = InetAddress.getByName("192.168.0.106");
        int port = 10086;
        DatagramPacket datagramPacket = new DatagramPacket(bytes,len,inetAddress,port);*/
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,InetAddress.getByName("192.168.0.106"),10086);

        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
