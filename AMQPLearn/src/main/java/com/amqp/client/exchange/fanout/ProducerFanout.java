package com.amqp.client.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerFanout
 * PackageName: com.amqp.client.exchange.fanout
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 16:58
 */
public class ProducerFanout {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.为连接工厂的属性赋值
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/jxms");
        connectionFactory.setUsername("jxms");
        connectionFactory.setPassword("jxms");
        Connection connection = connectionFactory.newConnection();
        //3.创建channel通道
        Channel channel = connection.createChannel();
        String exchageName = "test_fanout_exchange";

        String message = "hello exchange fount";
        //4.将消息持久化
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
        //5.发送消息
        channel.basicPublish(exchageName,"aaa",props,message.getBytes());
        channel.basicPublish(exchageName,"bbb",props,message.getBytes());
        channel.basicPublish(exchageName,"ccc",props,message.getBytes());
        channel.basicPublish(exchageName,"ddd",props,message.getBytes());
        channel.close();
        connection.close();
    }
}
