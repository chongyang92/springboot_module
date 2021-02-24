package com.weboot.springboot.socket.TCP.txttotxtfankui;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.106",10086);
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("pom.xml"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();//这里千万不要忘了
            bufferedWriter.flush();
        }

        /*bufferedWriter.write("886");
        bufferedWriter.newLine();
        bufferedWriter.flush();*/
        socket.shutdownOutput();//结束标记，推荐
        //这两个流会在socket.close时自动关闭
        //bufferedReader.close();
        //bufferedWriter.close();

        BufferedReader bufferedReaderfankui = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fankui = bufferedReaderfankui.readLine();
        System.out.println(fankui);
        socket.close();
    }
}
