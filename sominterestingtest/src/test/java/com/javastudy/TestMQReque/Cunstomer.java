package com.javastudy.TestMQReque;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * projectName: javastudy
 * packageName: com.javastudy.TestMQReque
 *
 * @author wangjingbiao
 * @date 2019-01-08 10:05
 * desc:
 * todo
 */
public class Cunstomer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.为连接工厂的属性赋值
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        //3.创建channel通道
        Channel channel = connection.createChannel();
        String exchageName = "backExc";
        String routingKey = "backmessage";
        String queue = "backQueue";
        channel.exchangeDeclare(exchageName,"topic",true);
        channel.queueDeclare(queue,true,false,false,null);
        channel.queueBind(queue,exchageName,routingKey);
        channel.basicConsume(queue,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(body.toString());
                System.out.println(properties.getMessageId());
                System.out.println(getChannel().getConnection().getAddress().getHostAddress());
                channel.basicNack(envelope.getDeliveryTag(),false,true);
            }
        });


    }
}
