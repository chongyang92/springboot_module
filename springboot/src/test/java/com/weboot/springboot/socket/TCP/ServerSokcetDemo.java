package com.weboot.springboot.socket.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSokcetDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);

        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println(new String(bytes,0,len));

        serverSocket.close();
    }
}
