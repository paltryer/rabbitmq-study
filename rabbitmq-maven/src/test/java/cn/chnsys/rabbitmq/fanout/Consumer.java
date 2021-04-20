package cn.chnsys.rabbitmq.fanout;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者1 fanout模式
 *
 * @author Wangchao
 * @version 1.0
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("orders", "fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue, "orders", "");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者->1：" + new String(body));
            }
        });

    }
}
