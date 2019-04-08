package com.imooc.socket.baseUdpDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @PackageName com.imooc.socket.baseUdpDemo
 * @Classname UdpProvider
 * @Date 19-4-8 下午4:36
 * @Created by wangjingbiao
 * @Description udp 用于提供服务
 */
public class UdpProvider {
    public static void main(String[] args) throws IOException {
        // 服务者的ip
        int providerPort = 20000;
        System.out.println("udp服务方启动");

        // 提供接口用于数据接收
        DatagramSocket ds = new DatagramSocket(providerPort);

        // 接收数据
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
        ds.receive(receivePack);

        // 接收到消息后取出所有信息
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLen);
        System.out.printf("UDPProvider receive from ip: %s ,port: %s ,data: %s\n",ip,port,data);

        // 给发送者回送1个报文
        String responseData = "收到的数据长度：" + dataLen;
        DatagramPacket responsePack = new DatagramPacket(responseData.getBytes(), responseData.getBytes().length, receivePack.getAddress(), receivePack.getPort());
        ds.send(responsePack);
        System.out.println("回送消息成功");
        ds.close();
    }
}
