package com.amqp.client.testProblem;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.recovery.AutorecoveringConnection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName: JavaStudy
 * ClassName: TestChannelIsDied
 * PackageName: com.amqp.client.testProblem
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-28 18:38
 */
public class TestChannelIsDied {
    private static Channel channel = null;
    private static Connection connection = null;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.18.186");
        factory.setPort(Integer.parseInt("5672"));
        factory.setUsername("jxms");
        factory.setPassword("jxms");
        factory.setVirtualHost("/");

        // 关键所在，指定线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 设置自动恢复
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(2);// 设置 没10s ，重试一次

        factory.setSharedExecutor(service);
        // 创建与RabbitMQ服务器的TCP连接
        connection = (AutorecoveringConnection) factory.newConnection();

        channel = connection.createChannel();

        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                // Beware that proper synchronization is needed here
                System.out.println("Handling channel shutdown... : " + cause);
                if (cause.isInitiatedByApplication()) {
                    System.out.println("Shutdown is initiated by application. Ignoring it.");
                } else {
                    System.out.println("Shutdown is NOT initiated by application. Resetting the channel.");
                    channel = null;
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            try{
                if(channel == null){
                    connection.createChannel();
                }
                channel.basicPublish("jxmstest","pams.person",null,"what is the f...?".getBytes());
            }catch (Exception e){
                System.out.println("send Message error");
            }
        }


//        Thread.sleep(3000);
//        if(channel == null){
//            channel = connection.createChannel();
//        }
//        channel.exchangeDeclare("jxmstest","topic",true);
    }
}
