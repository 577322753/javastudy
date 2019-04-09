package com.imooc.socket.UdpSearcher;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @PackageName com.imooc.socket.baseUdpDemo
 * @Classname UdpSearcher
 * @Date 19-4-8 下午4:36
 * @Created by wangjingbiao
 * @Description udp搜索者，用于搜索服务提供方
 */
public class Searcher {
    private static final int LISTEN_PORT = 30000;

    public static void main(String[] args) throws Exception {
        System.out.println("搜索者开始");
        // 服务者的ip
        //开启监听
        Listener listen = listen();
        // 发送广播，等待回复
        sendBroadcast();

        System.in.read();
        List<Device> devices = listen.getDevicesAndClose();
        for (Device device : devices) {
            System.out.println(device.toString());
        }
        System.out.println("搜索者退出");
    }

    /**
     * 监听
     */
    public static Listener listen() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISTEN_PORT, countDownLatch);
        new Thread(listener).start();
        // 必须等待开始监听后，再发送广播消息
        countDownLatch.await();
        return listener;

    }

    private static class Device {
        private int port;
        private String ip;
        private String sn;

        public Device(int port, String ip, String sn) {
            this.port = port;
            this.ip = ip;
            this.sn = sn;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", ip='" + ip + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    /**
     * 发送广播
     */
    public static void sendBroadcast() throws IOException {
        System.out.println("搜索方开始发送广播");
        // 作为搜索方，让系统自动分配端口
        DatagramSocket ds = new DatagramSocket();
        // 广播请求报文
        String requestData = MessageCreator.buildWithPort(LISTEN_PORT);
        DatagramPacket requestPacket = new DatagramPacket(requestData.getBytes(), requestData.getBytes().length);
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        requestPacket.setPort(20000);
        //发送搜索报文
        ds.send(requestPacket);
        System.out.println("搜索方发送广播结束");
    }

    private static class Listener implements Runnable {
        public final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> devices = new ArrayList<>();
        private boolean done = false;
        private DatagramSocket ds = null;

        public Listener(int listenPort, CountDownLatch countDownLatch) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("搜索方开始监听");
            //通知已经启动
            countDownLatch.countDown();
            try {
                ds = new DatagramSocket(listenPort);
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
                    System.out.printf("Searcher receive from ip: %s ,port: %s ,data: %s\n", ip, port, data);

                    String sn = MessageCreator.parseSn(data);
                    if (sn != null) {
                        Device device = new Device(port, ip, sn);
                        devices.add(device);
                    }
                }
            } catch (Exception e) {

            } finally {

            }
            System.out.println("搜索方监听结束");
        }

        public void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        List<Device> getDevicesAndClose() {
            done = true;
            close();
            return devices;
        }
    }
}
