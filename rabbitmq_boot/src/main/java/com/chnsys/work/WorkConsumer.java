package com.chnsys.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work模型 消费者
 *
 * @author wangchao
 * @version 1.0
 */
@Component
public class WorkConsumer {

    //@RabbitListener 可以标记在类上，需要配合RabbitHandler使用
    //也可以单独标记在方法上，效果等于 两个注解相加

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveMessages(String message){
        System.out.println("consumer111 receive message ="+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveMessages1(String message){
        System.out.println("consumer222 receive message ="+message);
    }

}
