package com.weboot.springboot.socket.TCP.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);

        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println(new String(bytes,0,len));

        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("服务端知道你来了".getBytes());

        serverSocket.close();
    }
}
