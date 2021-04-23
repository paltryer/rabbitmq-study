package com.chnsys.senior.ttl.service;

/**
 * 订单服务
 *
 * @author wangchao
 * @version 1.0
 */

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 声明队列的时候传的参数，使队列有过期时间
     */
    public void makeOrderTTL() {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + orderId);
        String exchangeName = "ttl_direct_exchange";
        String routingKey = "ttl";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    /**
     * 发送一个具有过期时间的消息
     * 声明队列的时候传的参数，使队列有过期时间
     */
    public void makeOrderTTLMessage() {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + orderId);
        String exchangeName = "ttl_direct_exchange";
        String routingKey = "ttlmessage";
        //自定义的消息
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000");
            message.getMessageProperties().setContentEncoding("UTF-8");
            return message;
        };
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId,messagePostProcessor);
    }

}
