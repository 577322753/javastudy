package com.amqp.client.limitConsumerSpeed;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerLimit
 * PackageName: com.amqp.client.limitConsumerSpeed
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-07 10:28
 */
public class ProducerLimit {
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
        //将消息持久化
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
        //5. 将队列发送
        for (int i = 0; i < 10; i++) {
            String message = "hello exchange direct : " + i;
            channel.basicPublish(exchageName,routingKey1,props,message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
