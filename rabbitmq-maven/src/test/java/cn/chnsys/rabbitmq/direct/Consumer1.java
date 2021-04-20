package cn.chnsys.rabbitmq.direct;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Routing key  ==  direct
 *
 * @author Wangchao
 * @version 1.0
 */
public class Consumer1 {


    /**
     * 接收 info、warning、error
     */
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Provider.EXCHANGE_NAME, "direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, Provider.EXCHANGE_NAME, "info");
        channel.queueBind(queue, Provider.EXCHANGE_NAME, "error");
        channel.queueBind(queue, Provider.EXCHANGE_NAME, "warning");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者->1:" + new String(body));
            }
        });
    }

}