package com.amqp.client.quickStart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: Consumer
 * PackageName: com.amqp.client.quickStart
 * Description:消费者
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 13:44
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.为连接工厂的属性赋值
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("jxms");
        connectionFactory.setPassword("jxms");
        connectionFactory.setVirtualHost("/jxms");
        Connection connection = connectionFactory.newConnection();
        //3.创建通道
        Channel channel = connection.createChannel();
        String queueName="test001";
        //4.声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        //5.创建消费者，并重写消费消息的方法
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("收到消息: " + msg);
                Map<String,Object> headers = properties.getHeaders();
                System.out.println(headers.get("my1"));
                System.out.println(properties.getClusterId());
                System.out.println(properties.getMessageId());
            }
        };
        //将消费者绑定到通道上
        channel.basicConsume(queueName,true,consumer);
    }
}
