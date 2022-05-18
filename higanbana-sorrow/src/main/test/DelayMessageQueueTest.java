import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.twip.higanbana.HiganbanaApplication;

import java.util.Date;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-24 20:54
 */
@Slf4j
@SpringBootTest(classes = HiganbanaApplication.class)
public class DelayMessageQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void test1(){
        rabbitTemplate.convertAndSend("flower_sea_delayed_exchange_higanbana","flower_sea_routinkey_higanbana","这是消息4",message -> {
            message.getMessageProperties().setDelay(20000);
            log.info("发送，当前时间{}，消息为{}", new Date(),"4");
            return message;
        });

        rabbitTemplate.convertAndSend("flower_sea_delayed_exchange_cherry","flower_sea_routinkey_cherry","这是消息1",message -> {
            message.getMessageProperties().setDelay(1000);
            log.info("发送，当前时间{}，消息为{}", new Date(),"1");
            return message;
        });

        rabbitTemplate.convertAndSend("flower_sea_delayed_exchange_higanbana","flower_sea_routinkey_higanbana","这是消息3",message -> {
            message.getMessageProperties().setDelay(10000);
            log.info("发送，当前时间{}，消息为{}", new Date(),"3");
            return message;
        });

        rabbitTemplate.convertAndSend("flower_sea_delayed_exchange_cherry","flower_sea_routinkey_cherry","这是消息2",message -> {
            message.getMessageProperties().setDelay(5000);
            log.info("发送，当前时间{}，消息为{}", new Date(),"2");
            return message;
        });
    }
}
