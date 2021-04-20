package cn.chnsys.rabbitmq.topic;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author Wangchao
 * @version 1.0
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Provider.EXCHANGE_NAME, "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, Provider.EXCHANGE_NAME, "user.#");
        channel.basicConsume(queue, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者->2" + new String(body));
            }
        });
    }

}
