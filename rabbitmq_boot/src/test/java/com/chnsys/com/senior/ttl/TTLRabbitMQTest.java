package com.chnsys.com.senior.ttl;

import com.chnsys.RabbitmqBootApplication;
import com.chnsys.senior.ttl.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 过期时间的单元测试
 *
 * @author wangchao
 * @version 1.0
 */
@SpringBootTest(classes = RabbitmqBootApplication.class)
@RunWith(SpringRunner.class)
public class TTLRabbitMQTest {

    @Autowired
    OrderService orderService;

    //设置过期队列 测试消息过期
    @Test
    public void ttlSendTest(){
        orderService.makeOrderTTL();
    }

    //测试具有过期时间的消息
    @Test
    public void ttlSendMessageTest(){
        orderService.makeOrderTTLMessage();
    }


}
