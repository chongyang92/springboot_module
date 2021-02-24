package com.weboot.springboot.socket.UDP.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();


        //自己封装键盘录入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            if(line.equals("886")){
                break;
            }
            byte[] bytes = line.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("192.168.0.106"),10086);
            datagramSocket.send(datagramPacket);
        }
        datagramSocket.close();
    }
}
