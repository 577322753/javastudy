package com.imooc.chat.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @PackageName com.imooc.chat.websocket
 * @Classname WsServerInitialzer
 * @Date 19-3-30 下午2:19
 * @Created by wangjingbiao
 * @Description 监听器处理
 */
public class WsServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加处理类
        // 1.websocket基于http协议，此处添加http的编解码器
        pipeline.addLast(new HttpServerCodec());
        // 2.对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 3.对httpMessage进行聚合，聚合成fullHttpRequest
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        //======以上是支持http协议=======
        // 4.增加websocket服务器处理的协议
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 5. 增加消息处理逻辑
        pipeline.addLast(new ChatHandler());
    }
}
