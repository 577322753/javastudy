package com.imooc.socket.UdpSearcher;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

/**
 * @PackageName com.imooc.socket.baseUdpDemo
 * @Classname UdpProvider
 * @Date 19-4-8 下午4:36
 * @Created by wangjingbiao
 * @Description udp 用于提供服务
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        String sn = UUID.randomUUID().toString();
        ReceiveRunner runner = new ReceiveRunner(sn);
        new Thread(runner).start();

        // 读取任意字符退出程序
        System.in.read();
        runner.exit();
    }

    private static class ReceiveRunner implements Runnable {
        private final String sn;
        private boolean done = false;
        private DatagramSocket ds = null;

        public ReceiveRunner(String sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            try {
                // 构建监听
                ds = new DatagramSocket(20000);
                System.out.println("服务方监听启动");

                // 持续接收数据
                while (!done) {
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

                    // 从消息包里，解析出指定的回传端口
                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1) {
                        // 证明给出的端口是有效的，回送数据
                        String responseData = MessageCreator.buildWithSn(sn);
                        DatagramPacket responsePacket = new DatagramPacket(responseData.getBytes(),
                                responseData.getBytes().length,receivePack.getAddress(),responsePort);
                        ds.send(responsePacket);
                    }
                }
            } catch (Exception e) {

            } finally {
                close();
            }
            System.out.println("服务方监听结束");

        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        void exit() {
            done = true;
            close();
        }
    }
}
