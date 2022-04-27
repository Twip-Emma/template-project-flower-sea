package top.twip.higanbana.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-27 13:13
 */
@Component
@RabbitListener(queues = "flower_sea_dlx_queue")
public class BlockRabbitMQListener {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("接收到消息：" + msg + new Date());
    }
}
