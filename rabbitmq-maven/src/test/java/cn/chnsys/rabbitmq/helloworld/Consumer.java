package cn.chnsys.rabbitmq.helloworld;

import cn.chnsys.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者 demo 测试类
 *
 * @author Wangchao
 * @version 1.0
 */
public class Consumer {

    //需要以main函数的形式，@Test测试不支持多线程接收消息
    @Test
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        //绑定通道
        channel.queueDeclare("hello", false, false, false, null);
        //消费消息
        /**
         * 参数1：消费哪个队列
         * 参数2：开始消息自动确认机制
         * 参数3：消费时回调接口
         */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            /**
             重写方法当有消息时，触发回调函数
             * @param body 消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });
//        channel.close();
//        connection.close();

    }

}
