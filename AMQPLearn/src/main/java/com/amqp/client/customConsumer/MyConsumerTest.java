package com.amqp.client.customConsumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: MyConsumerTest
 * PackageName: com.amqp.client.customConsumer
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-07 10:06
 */
public class MyConsumerTest {
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
        String queueName="test002";
        //4.声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        MyConsumer consumer = new MyConsumer(channel);
        //将消费者绑定到通道上
        channel.basicConsume(queueName,true,consumer);
    }
}
