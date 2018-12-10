package com.amqp.client.backQueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ConsumerLimit
 * PackageName: com.amqp.client.limitConsumerSpeed
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-07 10:28
 */
public class ConsumerBackQueue {
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
        String exchageName = "test_ack_exchange";
        String exchangeType = "topic";
        String queueName="test_ack_queue";
        String routingKey = "ack.#";
        // 4.1 声明交换机
        channel.exchangeDeclare(exchageName,exchangeType,true,false,false,null);
        // 4.2 声明队列
        channel.queueDeclare(queueName,true,false,false,null);
        // 4.3 将交换机与队列绑定
        channel.queueBind(queueName,exchageName,routingKey);

        //5.创建消费者，并重写消费消息的方法
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Integer i = (Integer) properties.getHeaders().get("num");
                if(i == 1){
                    //第三个参数控制重回队列
                    channel.basicNack(envelope.getDeliveryTag(),false,true);
                    try {
                        System.out.println("sleep 5s");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    String msg = new String(body);
                    System.out.println("收到消息: " + msg +" : " + i);
                }
            }
        };
        //限制流量相关的策略
       // channel.basicQos(0,3,false);
        channel.basicConsume(queueName,false,consumer);
    }
}
