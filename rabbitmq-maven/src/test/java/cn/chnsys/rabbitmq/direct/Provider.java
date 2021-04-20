package cn.chnsys.rabbitmq.direct;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Routing - Direct 直连 <b>routing完全一致</b>
 * <p>
 * 队列与交换机绑定，需要指定一个RoutingKey
 * 消息的发送方在向Exchange 发送消息时，也必须指定RoutingKey
 * Exchange 不再把消息交给每个绑定的队列，而是根据消息的RoutingKey进行判断，与Routingkey <b>完全一致</b>，才会接收到消息
 *
 * @author Wangchao
 * @version 1.0
 */
public class Provider {

    public static final String EXCHANGE_NAME = "log-direct";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();
        //声明一个路由直连的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String routingKey = "info";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, ("这是direct模型发布的基于routingKey：{" + routingKey + "} 发送的消息").getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }

}
