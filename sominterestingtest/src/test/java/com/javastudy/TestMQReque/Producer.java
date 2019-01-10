package com.javastudy.TestMQReque;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
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
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.为连接工厂的属性赋值
        connectionFactory.setHost("192.168.18.226");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("jxms");
        connectionFactory.setPassword("jxms");
        Connection connection = connectionFactory.newConnection();
        //3.创建channel通道
        Channel channel = connection.createChannel();
        String exchageName = "simpToJxms";
        String routingKey = "simp.groupAndMember.info";
        String queue = "simpToJxms";
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().messageId(UUID.randomUUID().toString()).deliveryMode(2).timestamp(new Date()).build();
        String message = "{\"owner\":\"ff8080816771eb110167a6cf13f70591\",\"currentTime\":1546919205496,\"lastupdatetime\":1546919205480,\"jointimes\":\"154590,0,0,82313566,1546919205480\",\"memsum\":2,\"groupid\":\"8875b5035d444d0d9fb758eb0c160892\",\"name\":\"yy测试\",\"icon\":null,\"deptId\":\"ff8080816771eb110167a6539dee0548\",\"groupnum\":\"1609\",\"usernames\":\"ff8080816771eb110167a6cf13f70591,ff8080816771eb110167a651ec16052c\"}";
        channel.basicPublish(exchageName,routingKey,properties,message.getBytes());
        channel.close();
    }
}
