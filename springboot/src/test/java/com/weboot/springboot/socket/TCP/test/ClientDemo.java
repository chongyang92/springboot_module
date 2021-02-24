package com.weboot.springboot.socket.TCP.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.106",10086);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("客户端我发数据了".getBytes());
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println(new String(bytes,0,len));

        socket.close();
    }
}
