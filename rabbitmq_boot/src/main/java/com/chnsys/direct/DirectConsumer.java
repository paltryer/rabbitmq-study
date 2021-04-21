package com.chnsys.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 */
@Component
public class DirectConsumer {


    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "boot-direct", type = "direct"), key = {"info", "error", "warning"})})
    public void receive(String message) {
        System.out.println("11111->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "boot-direct", type = "direct"), key = {"error"})})
    public void receive1(String message) {
        System.out.println("2222->"+message);
    }

}
