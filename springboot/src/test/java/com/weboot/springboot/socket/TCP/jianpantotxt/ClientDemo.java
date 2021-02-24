package com.weboot.springboot.socket.TCP.jianpantotxt;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.106",10086);
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();//这里千万不要忘了
            bufferedWriter.flush();
        }
        //这两个流会在socket.close时自动关闭
        //bufferedReader.close();
        //bufferedWriter.close();
        socket.close();
    }
}
