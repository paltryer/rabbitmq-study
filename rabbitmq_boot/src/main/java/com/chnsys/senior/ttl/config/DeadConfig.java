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
 *死信队列的配置初始化
 *
 * @author wangchao
 * @version 1.0
 */
@Configuration
public class DeadConfig {

    /**
     *
     * @return
     */


    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_direct_exchange", true, false);
    }

    /**
     * 设置过期时间
     *
     * @return 带有过期时间的队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("dead.direct.queue",true);
    }

    /**
     * 将交换机和队列绑定，指定队列的路由为ttl
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }


}
