package com.weboot.springboot.socket.TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


//客户端
public class SocketDemo {
    public static void main(String[] args) throws IOException {
        //Socket socket = new Socket(InetAddress.getByName("192.168.0.106"),10086);
        Socket socket = new Socket("192.168.0.106",10086);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello我来了".getBytes());

        outputStream.close();

    }
}
