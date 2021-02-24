package com.weboot.springboot.socket.TCP.txttotxt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);

        Socket accept = serverSocket.accept();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("uu.txt"));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(line);
        }

        //这里需要将bufferedWriter关闭
        bufferedWriter.close();
        serverSocket.close();
    }
}
