package com.imooc.socket.UdpSearcher;

/**
 * @PackageName com.imooc.socket.UdpSearcher
 * @Classname MessageCreator
 * @Date 19-4-8 下午5:11
 * @Created by wangjingbiao
 * @Description 消息创建者，用于构建口令
 */
public class MessageCreator {
    /**
     * 收到暗号后的响应数据
     */
    public static final String SN_HEADER = "SN_HEADER:";
    /**
     * 发送暗号，并携带回送端口
     */
    public static final String PORT_HEADER = "PORT_HEADER:";

    /**
     * 构建发送方暗号
     *
     * @param port
     * @return
     */
    public static String buildWithPort(int port) {
        return PORT_HEADER + port;
    }

    /**
     * 从发送方暗号中解析出端口
     *
     * @param data
     * @return
     */
    public static int parsePort(String data) {
        if (data.startsWith(PORT_HEADER)) {
            return Integer.parseInt(data.substring(PORT_HEADER.length()));
        }
        return -1;
    }

    /**
     * 构建密文消息
     * @param sn
     * @return
     */
    public static String buildWithSn(String sn) {
        return SN_HEADER + sn;
    }

    /**
     * 从密文消息中解析出数据
     * @param data
     * @return
     */
    public static String parseSn(String data) {
        if (data.startsWith(SN_HEADER)) {
            return data.substring(SN_HEADER.length());
        }
        return null;
    }
}
