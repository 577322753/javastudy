package com.amqp.client.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
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
public class ConsumerDlx {
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
        String exchageName = "test_dlx_exchange";
        String exchangeType = "topic";
        String queueName="test_dlx_queue";
        String routingKey = "dlx.#";

        // 生成队列，并指定死信队列
        java.util.Map<String,Object> arguments = new HashMap<String,Object>();
        arguments.put("x-dead-letter-exchange","my_dead_exchange");
        channel.exchangeDeclare(exchageName,exchangeType,true,false,false,null);
        channel.queueDeclare(queueName,true,false,false,arguments);
        channel.queueBind(queueName,exchageName,routingKey);
        // 声明死信队列
        channel.exchangeDeclare("my_dead_exchange","topic",true,false,false,null);
        channel.queueDeclare("my_dead_queue",true,false,false,null);
        channel.queueBind("my_dead_queue","my_dead_exchange","#");


        //5.创建消费者，并重写消费消息的方法
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Integer i = (Integer) properties.getHeaders().get("num");
                String msg = new String(body);
                System.out.println("收到消息: " + msg +" : " + i);

                if(i % 2 == 0){
                    //第三个参数控制重回队列
                    channel.basicNack(envelope.getDeliveryTag(),false,false);
                }else{
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //限制流量相关的策略
       // channel.basicQos(0,3,false);
        channel.basicConsume(queueName,false,consumer);
    }
}
