package com.chnsys.rabbitmq.hello;

import com.chnsys.RabbitmqBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot连接RabbitMQ 测试类
 *
 * @author wangchao
 * @version 1.0
 */
//会启动容器
@SpringBootTest(classes = RabbitmqBootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    /**
     * 生产者并不能去声明一个交换机或队列
     */


    //注入RabbitTemplate
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessageTest() {
        //转化并发送
        rabbitTemplate.convertAndSend("hello", "spring boot hello world!");
    }

    /**
     * work 多个消费者声明一个队列，生产者发送消息，只能被一个消费者消费；
     */
    @Test
    public void workSendTest() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型->第" + i + "条消息");
        }
    }

    /**
     * fanout 模型  广播模型，  所有该交换机的队列都可以接收到
     */
    @Test
    public void fanoutSentTest() {
        rabbitTemplate.convertAndSend("boot-logs", "", "fanout模型的消息！");
    }

    /**
     * routing -direct  固定路由，可以绑定多个，但是必须完全匹配
     */
    @Test
    public void routingSendTest() {
        rabbitTemplate.convertAndSend("boot-direct", "error", "发送error的key的路由信息！");
    }

    /**
     * routing -direct  动态路由
     */
    @Test
    public void topicSendTest() {
        rabbitTemplate.convertAndSend("boot-topic", "user.1111", "发送user.save的key的路由信息！");
    }

}
