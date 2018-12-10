package com.amqp.client.exchange.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerTopic
 * PackageName: com.amqp.client.exchange.topic
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 15:38
 */
public class ProducerTopic {
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
        String exchageName = "test_topic_exchange";
        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete";
        String routingKey4 = "news.add";

        String[] message = new String[]{"hello exchange direct 1","hello exchange direct 2","hello exchange direct 3","hello exchange direct 4"};
        //将消息持久化
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
        //5. 将队列发送
        channel.basicPublish(exchageName,routingKey1,props,message[1].getBytes());
        channel.basicPublish(exchageName,routingKey2,props,message[2].getBytes());
        channel.basicPublish(exchageName,routingKey3,props,message[3].getBytes());
        channel.basicPublish(exchageName,routingKey4,props,message[4].getBytes());
        channel.close();
        connection.close();
    }
}
