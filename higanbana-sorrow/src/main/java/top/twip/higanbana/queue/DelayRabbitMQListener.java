package top.twip.higanbana.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-24 20:50
 */
@Component
@RabbitListener(queues = "flower_sea_delayed_queue")
public class DelayRabbitMQListener {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("接收到消息：" + msg);
    }
}
