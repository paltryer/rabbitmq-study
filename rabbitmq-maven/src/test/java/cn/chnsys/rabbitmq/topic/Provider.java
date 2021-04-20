package cn.chnsys.rabbitmq.topic;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 动态路由
 * topic 和direct 相比，都是可以根据routing key 把消息分到不同队列；
 * 只不过topic类型Exchange可以让队列在绑定routing key的时候使用   通配符
 * 这种模式 routing key 一般都是由一个或者多个单词组成，多个单词之间以 。 分割 例如   item。insert
 * * 匹配一个单词
 * # 匹配0到多个单词
 *
 * @author Wangchao
 * @version 1.0
 */
public class Provider {

    public static final String EXCHANGE_NAME = "ex-topic";

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String routingKey = "user";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, ("topic is {" + routingKey + "}").getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel, connection);

    }

}
