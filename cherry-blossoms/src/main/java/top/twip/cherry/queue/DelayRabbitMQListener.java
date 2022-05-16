package top.twip.cherry.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-16 16:09
 */
@Component
@RabbitListener(queues = "flower_sea_delayed_queue_cherry")
public class DelayRabbitMQListener {

    @RabbitHandler
    public void process(String msg){
        System.out.println("樱花接收到消息：" + msg + new Date());
    }
}
