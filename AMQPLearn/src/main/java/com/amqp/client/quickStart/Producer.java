package com.amqp.client.quickStart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: Producer
 * PackageName: com.amqp.client.quickStart
 * Description:生产者
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 13:43
 */
public class Producer {
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
        for (int i = 0; i < 5; i++) {
            //4.将消息发布出去
            /**
             * basePublish需要的参数
             * exchange
             * routingKey 路由名称，如果exchange为空的话，会默认分配exchange，
             * 默认的路由规则是根据routingkey来找到同名称的队列
             * props 附加属性
             * body 消息体
             */
            channel.basicPublish("","test001",null,"hello rabbitMq".getBytes());
        }

        channel.close();
        connection.close();
    }
}
