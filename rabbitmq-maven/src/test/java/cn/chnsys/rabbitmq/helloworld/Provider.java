package cn.chnsys.rabbitmq.helloworld;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;

/**
 * 生产者实体类
 * 没有使用SpringBoot版本
 *
 * @author Wangchao
 * @version 1.0
 */

public class Provider {

    /**
     * 测试连接 rabbitmq   --hello rabbitmq
     */
    @Test
    public void testSendMessage() throws IOException {
        //创建连接对象
        Connection connection = RabbitMqUtils.getConnection();
        //连接 获取 通道
        assert connection != null;
        Channel channel = connection.createChannel();
        /*
         声明一个队列
         参数1；队列名称    如果队列不存在，自动创建
         参数2：定义队列特性是否要持久化  true：持久化   false：不持久化
         参数3：exclusive：是否独占队列  当前队列只由当前队列使用
         参数4：autoDelete:是否在消费完成后自动删除队列
         参数5：额外附加参数
         */
        channel.queueDeclare("hello", false, false, false, null);
        //发布消息
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "good man!".getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }

}
