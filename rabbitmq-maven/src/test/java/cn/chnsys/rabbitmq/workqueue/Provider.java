package cn.chnsys.rabbitmq.workqueue;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * work 形式的多消费者
 * 多work形式，多个消费者进行消息消费  在默认情况下：consumer1和consumer2是平均消费的
 *
 * @author Wangchao
 * @version 1.0
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", "work", null, (i + "->hello worker queue").getBytes());
        }
        RabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
