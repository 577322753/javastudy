package com.imooc.socket.baseTcpDemo;

import java.io.*;
import java.net.*;

/**
 * ProjectName: JavaStudy
 * ClassName: Client
 * PackageName: com.imooc.socket.baseTcpDemo
 * Descriptioni: 简单的socket客户端
 *
 * @author: wangjingbiao
 * @date: 2018-10-20 10:40
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        //读取流的超时时间
        socket.setSoTimeout(3000);

        //连接端地址，并设置连接超时时间
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已经发起服务器连接，并进入后续流程");
        System.out.println("客户端信息： " + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务端信息： " + socket.getInetAddress() + " P:" + socket.getPort());

        try {
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已经退出～");
    }

    private static void todo(Socket socket) throws IOException {
        //获取键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        //获取socket输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        //获取socket输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            //将键盘输入的一行，发送到服务端
            String str = input.readLine();
            socketPrintStream.println(str);

            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println("服务端说: " + echo);
            }
        } while (flag);

        //释放资源
        socketBufferedReader.close();
        socketPrintStream.close();
        input.close();
    }
}
