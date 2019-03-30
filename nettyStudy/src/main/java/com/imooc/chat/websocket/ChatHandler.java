package com.imooc.chat.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @PackageName com.imooc.chat.websocket
 * @Classname ChatHandler
 * @Date 19-3-30 下午2:28
 * @Created by wangjingbiao
 * @Description 处理消息
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // 记录所有的channel
    private static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        System.out.println("接收到的数据 " + content);
        /**
         * 向所有的客户端刷出收到的数据
         */
        for (Channel channel : users) {
            if (channel != ctx.channel()){
                channel.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + " : " + content));
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.id().asLongText() + "  上线了");
        users.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 其实会自动移除掉
        System.out.println(ctx.channel().id().asLongText() + "  下线了");
        users.remove(ctx.channel());
    }
}
