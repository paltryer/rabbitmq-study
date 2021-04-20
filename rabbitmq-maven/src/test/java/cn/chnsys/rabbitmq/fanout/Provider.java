package cn.chnsys.rabbitmq.fanout;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * fanout模型交换机   生产者
 * 广播模型
 * 可以有多个消费者
 * 每个消费者有自己的queue
 * 每个队列都要绑定到交换机Exchange
 * 生产者发送的消息，只能发送到交换机，交换机来决定发给哪个队列，生产者无法决定
 * 交换机把消息发送给绑定过的所有队列
 * 队列的消费者都能拿到消息。
 * 实现了一条消息被多个消费者消费。
 *
 * @author Wangchao
 * @version 1.0
 */
public class Provider {

    /**
     * 声明fanout的交换机，发送消息
     *
     * @param args 参数
     * @throws IOException IO异常
     */
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();
        //声明交换机     type：fanout 广播类型
        channel.exchangeDeclare("orders", "fanout");
        //发送消息
        channel.basicPublish("orders", "", null, "fanout type message".getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
