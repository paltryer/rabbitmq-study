package com.chnsys.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 广播模型 消费者
 *
 * @author wangchao
 * @version 1.0
 */

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "boot-logs", type = "fanout"))})
    public void receiveMessage(String message) {
        System.out.println("消费者111->" + message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "boot-logs", type = "fanout"))})
    public void receiveMessage1(String message) {
        System.out.println("消费者222->" + message);
    }

}
