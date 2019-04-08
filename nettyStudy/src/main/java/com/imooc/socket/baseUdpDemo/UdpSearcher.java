package com.imooc.socket.baseUdpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @PackageName com.imooc.socket.baseUdpDemo
 * @Classname UdpSearcher
 * @Date 19-4-8 下午4:36
 * @Created by wangjingbiao
 * @Description udp搜索者，用于搜索服务提供方
 */
public class UdpSearcher {
    public static void main(String[] args) throws Exception {
        // 服务者的ip
        int searchPort = 20000;
        System.out.println("udp搜索方启动");

        // 作为搜索方，让系统自动分配
        DatagramSocket ds = new DatagramSocket();

        // 构建报文，并发送消息
        String data = "hello,world! i am UDP";
        DatagramPacket requestPack = new DatagramPacket(data.getBytes(), data.getBytes().length);
        requestPack.setAddress(InetAddress.getLocalHost());
        requestPack.setPort(searchPort);
        ds.send(requestPack);
        System.out.println("发送消息成功");


        // 接收数据
        final byte[] buf = new byte[512];
        DatagramPacket receiveResponsePack = new DatagramPacket(buf, buf.length);
        ds.receive(receiveResponsePack);

        // 接收到消息后取出所有信息
        String ip = receiveResponsePack.getAddress().getHostAddress();
        int port = receiveResponsePack.getPort();
        int dataLen = receiveResponsePack.getLength();
        String receiveResponseData = new String(receiveResponsePack.getData(), 0, dataLen);
        System.out.printf("UDPProvider receive from ip: %s ,port: %s ,data: %s\n",ip,port,receiveResponseData);
        ds.close();
    }
}
