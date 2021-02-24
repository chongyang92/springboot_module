package com.weboot.springboot.socket.TCP.txttotxtthread;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;
    public ServerThread(Socket accept) {
        this.socket = accept;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            File file = new File("copy.xml");
            int count = 0;
            while(file.exists()){
                count++;
                file = new File(count + "copy.xml");
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            //反馈给客户端
            BufferedWriter bufferedWriterFankui = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriterFankui.write("服务器接收文件成功");
            bufferedWriterFankui.newLine();
            bufferedWriterFankui.flush();
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
