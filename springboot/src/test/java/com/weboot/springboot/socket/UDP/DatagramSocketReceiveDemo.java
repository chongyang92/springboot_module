package com.weboot.springboot.socket.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramSocketReceiveDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        //存放数据的包裹
        byte[] bytes = new byte[10];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);

        datagramSocket.receive(datagramPacket);

        //拿到数据缓冲区，就是bytes，1024个字节，不管里面有没有数据
        byte[] packetData = datagramPacket.getData();
        System.out.println("数据是:"+new String(packetData,0,datagramPacket.getLength()));



        /*byte[] bytes = "hello,UDP我来了".getBytes();
        *//*int len = bytes.length;
        InetAddress inetAddress = InetAddress.getByName("192.168.0.106");
        int port = 10086;
        DatagramPacket datagramPacket = new DatagramPacket(bytes,len,inetAddress,port);*//*
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,InetAddress.getByName("192.168.0.106"),10086);

        datagramSocket.send(datagramPacket);*/
        datagramSocket.close();
    }
}
