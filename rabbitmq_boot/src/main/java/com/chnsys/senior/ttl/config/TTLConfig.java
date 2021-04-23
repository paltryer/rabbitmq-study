package com.chnsys.senior.ttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 测试队列的过期时间
 * 声明bean，在容器启动时，队列已经声明
 *
 * @author wangchao
 * @version 1.0
 */
@Configuration
public class TTLConfig {


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("ttl_direct_exchange", true, false);
    }

    /**
     * 设置过期时间
     *
     * @return 带有过期时间的队列
     */
    @Bean
    public Queue directQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置过期时间 参数
        args.put("x-message-ttl", 5000);
        //设置死信队列  交换机名称
        args.put("x-dead-letter-exchange", "dead_direct_exchange");
        //设置死信队列  路由    由于该死信交换机为direct模式，所以需要指定routingKey；  如果死信队列的交换机为fanout（广播模型） 则不需要指定路由
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.direct.queue", true, false, false, args);
    }

    /**
     * 将交换机和队列绑定，指定队列的路由为ttl
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("ttl");
    }


    /**
     * 声明一个交换机、队列，并且将 队列绑定到交换机上  同时指定队列的路由
     * 在orderService中调用发送消息到指定交换机上，交换机通过路由匹配到具体发送给哪些队列
     * <p>
     * 设置过期时间
     *
     * @return 带有过期时间的队列
     */
    @Bean
    public Queue directTTLMessageQueue() {
        return new Queue("ttl.message.direct.queue", true);
    }

    /**
     * 将交换机和队列绑定，指定队列的路由为ttl
     */
    @Bean
    public Binding directTTLMessageBinding() {
        return BindingBuilder.bind(directTTLMessageQueue()).to(directExchange()).with("ttlmessage");
    }


}
