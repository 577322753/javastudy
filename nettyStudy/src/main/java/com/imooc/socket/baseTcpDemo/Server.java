package com.imooc.socket.baseTcpDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ProjectName: JavaStudy
 * ClassName: Server
 * PackageName: com.imooc.socket.baseTcpDemo
 * Description: 一个简单的echo服务器，将接收到的话返回给客户端
 *
 * @author: wangjingbiao
 * @date: 2018-10-20 10:38
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("服务器准备就绪～");
        System.out.println("服务器信息： " + serverSocket.getInetAddress() + " P:" + serverSocket.getLocalPort());

        //一直接收客户端的连接
        while (true) {
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            new Thread(clientHandler).start();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("新客户端连接" + socket.getInetAddress() + " P:" + socket.getPort());

            try {
                PrintStream socketOutPut = new PrintStream(socket.getOutputStream());
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)) {
                        flag = false;
                        socketOutPut.println("bye");
                    } else {
                        System.out.println("\"" + socket.getInetAddress() + " P:" + socket.getPort() + "\"客户端说：" + str);
                        socketOutPut.println("这句话长度为：" + str.length());
                    }
                } while (flag);

            } catch (Exception e) {
                System.out.println("客户端异常断开");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
