package com.imooc.chat.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @PackageName com.imooc.chat.websocket
 * @Classname WsServer
 * @Date 19-3-30 下午2:09
 * @Created by wangjingbiao
 * @Description 启动一个websocket端服务器
 */
public class WsServer {
    public static final int port = 8088;

    public static void main(String[] args) throws Exception {
        /**
         * 主线程组，处理连接接入
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        /**
         * 从线程组，处理数据读取
         */
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            /**
             * 1. 创建启动器，并赋值工作线程组
             */
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup);
            /**
             * 2. 设置channel类型，采用nio
             */
            server.channel(NioServerSocketChannel.class);
            /**
             * 3. 设置子处理器，对数据进行处理
             */
            server.childHandler(new WsServerInitialzer());
            /**
             * 4. 绑定端口，并进行监听，采用同步方式
             */
            ChannelFuture future = server.bind(8088).sync();
            /**
             * 5. 结束监听后，关闭所有channel
             */
            future.channel().closeFuture().sync();
        }finally {
            // 6. 最后关掉两个工作线程组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
