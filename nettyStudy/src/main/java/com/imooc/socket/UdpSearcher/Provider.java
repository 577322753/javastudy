package com.imooc.socket.UdpSearcher;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @PackageName com.imooc.socket.baseUdpDemo
 * @Classname UdpProvider
 * @Date 19-4-8 下午4:36
 * @Created by wangjingbiao
 * @Description udp 用于提供服务
 */
public class Provider {
    public static void main(String[] args) throws IOException {

    }

    private class receiveRunner implements Runnable {
        private final String sn;
        private boolean done = false;
        private DatagramSocket ds = null;

        public receiveRunner(String sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            try {
                // 构建监听
                ds = new DatagramSocket(20000);
                while (!done) {
                    System.out.println("udp服务方启动");

                    // 接收数据
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
                    ds.receive(receivePack);

                    // 接收到消息后取出所有信息
                    String ip = receivePack.getAddress().getHostAddress();
                    int port = receivePack.getPort();
                    int dataLen = receivePack.getLength();
                    String data = new String(receivePack.getData(), 0, dataLen);
                    System.out.printf("UDPProvider receive from ip: %s ,port: %s ,data: %s\n", ip, port, data);

                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1){

                    }
                }
            } catch (Exception e) {

            } finally {
                close();
            }

        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        void exit() {
            done = true;
        }
    }
}
