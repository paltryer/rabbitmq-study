package com.chnsys.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 * @description TODO
 * @date 2021/4/21 18:39
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(name = "boot-topic", type = "topic"), key = {"user.*"})})
    public void receive(String message) {
        System.out.println("11111->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(name = "boot-topic", type = "topic"), key = {"*.info"})})
    public void receive1(String message) {
        System.out.println("22222->"+message);
    }

}
