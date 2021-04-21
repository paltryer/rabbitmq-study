package com.chnsys.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * SpringBoot 消费者
 *
 * @author wangchao
 * @version 1.0
 */

//声明为 一个 组件   并且监听rabbitmq的消息
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
/**
 * 声明一个队列      默认是：持久化、非独占、不自动删除   true，false，false
 */
public class Consumer {

    /**
     * 接收到消息时，会回调 @RabbitHandler注解 的方法
     * 会将消息封装到参数的String中
     */
    @RabbitHandler
    public void receiveMessages(String message) {
        System.out.println("consumer receive message =" + message);
    }

}
