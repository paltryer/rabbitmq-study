package cn.chnsys.rabbitmq.workqueue;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * work 模式 消费者1
 *
 * @author Wangchao
 * @version 1.0
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        //每次只能接收一个消息
        channel.basicQos(1);

        channel.queueDeclare("work", true, false, false, null);
        //autoAck:true 自动确认，不需要手动ack
        //channel.basicConsume("work", true, new DefaultConsumer(channel) {
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1==" + new String(body));
                //消息确认 参数1：消息的标识  参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
