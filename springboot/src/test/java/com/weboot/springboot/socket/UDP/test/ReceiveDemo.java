package com.weboot.springboot.socket.UDP.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            datagramPacket.hashCode();
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        }
        //一直接数据，不用close了，执行不到
        //datagramSocket.close();
    }
}
