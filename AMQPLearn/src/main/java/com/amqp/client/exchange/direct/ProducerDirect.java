package com.amqp.client.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerDirect
 * PackageName: com.amqp.client.exchange.direct
 * Description:直连生产者
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 15:02
 */
public class ProducerDirect {
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
        String exchageName = "test_direct_exchange";
        String routingKey = "test.direct";
        //4. 声明交换机,如果已存在的交换机可以不用声明，直接根据名称来使用
        String exchangeType = "direct";
        channel.exchangeDeclare(exchageName,exchangeType,true,false,false,null);

        String message = "hello exchange direct";
        //5. 将队列发送
        channel.basicPublish(exchageName,routingKey,null,message.getBytes());
        channel.close();
        connection.close();
    }
}
