package com.amqp.client.backQueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
public class ProducerBackQueue {
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
        String exchageName = "test_ack_exchange";
        String routingKey = "ack.save";
        String message = "hello ack test";
        //5. 将队列发送
        for (int i = 0; i < 10; i++) {
            Map<String,Object> headers = new HashMap<>();
            headers.put("num",i);

            AMQP.BasicProperties props = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("utf-8")
                    .headers(headers)
                    .build();
            channel.basicPublish(exchageName,routingKey,props,message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
