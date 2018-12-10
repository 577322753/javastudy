package com.amqp.client.returnMessage;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerReturn
 * PackageName: com.amqp.client.returnMessage
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-07 09:33
 */
public class ProducerReturn {
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
        String exchange = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";
        String msg = "please return message";
        //mandatory设置为true，可以收到返回监听
        channel.basicPublish(exchange,routingKey,true,null,msg.getBytes());
        channel.basicPublish(exchange,routingKeyError,true,null,msg.getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("-----------------handler return----------------");
                System.out.println("replyCode: "+replyCode);
                System.out.println("replyText: "+replyText);
                System.out.println("exchange: "+exchange);
                System.out.println("routingKey: "+routingKey);
                System.out.println("properties: "+properties);
                System.out.println("body: "+new String(body));
            }
        });
    }
}
