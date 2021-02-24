package com.weboot.springboot.socket.TCP.txttotxtthread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);
        
        while (true){
            //监听客户端连接返回一个对应的Socket对象
            Socket accept = serverSocket.accept();
            new Thread(new ServerThread(accept)).start();

        }




















        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("uu.txt"));
        BufferedWriter bufferedWriterfankui = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {//读到客户端发来的shutdownOutput数据时，也结束
            *//*if(line.equals("886")){
                break;
            }*//*
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(line);
        }
        bufferedWriterfankui.write("接收文件成功");
        bufferedWriterfankui.newLine();
        bufferedWriterfankui.flush();
        //这里需要将bufferedWriter关闭
        bufferedWriter.close();
        serverSocket.close();*/
    }
}
