package com.amqp.client.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ConsumerDirect
 * PackageName: com.amqp.client.exchange.direct
 * Description: 直连消费者
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 15:02
 */
public class ConsumerDirect {
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
        //4. 将通道中的交换机队列声明，并绑定关系
        String exchageName = "test_direct_exchange";
        String exchangeType = "direct";
        String queueName="test_direct_queue";
        String routingKey = "test.direct";
        // 4.1 声明交换机
        channel.exchangeDeclare(exchageName,exchangeType,true,false,false,null);
        // 4.2 声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        // 4.3 将交换机与队列绑定
        channel.queueBind(queueName,exchageName,routingKey);

        //5.创建消费者，并重写消费消息的方法
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("收到消息: " + msg);
            }
        };
        //将消费者绑定到通道上
        channel.basicConsume(queueName,true,consumer);
    }
}
