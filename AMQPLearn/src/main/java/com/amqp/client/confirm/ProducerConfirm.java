package com.amqp.client.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: ProducerConfirm
 * PackageName: com.amqp.client.confirm
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-06 20:26
 */
public class ProducerConfirm {
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
        //4.指定消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingkey = "confirm.save";
        String message = "it is hello";
        //5.发布消息
        channel.basicPublish(exchangeName,routingkey,null,message.getBytes());
        //6.监听添加确认消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-----ack --------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-----no ack --------");
            }
        });
        //5.发布消息
        channel.basicPublish(exchangeName,routingkey,null,message.getBytes());
    }
}
