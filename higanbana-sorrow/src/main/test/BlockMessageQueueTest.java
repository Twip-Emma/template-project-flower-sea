import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.twip.higanbana.HiganbanaApplication;

import java.util.Date;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-27 13:09
 */
@Slf4j
@SpringBootTest(classes = HiganbanaApplication.class)
public class BlockMessageQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void test1(){
        rabbitTemplate.convertAndSend("flower_sea_dlx_exchange", "routing-key-delay","我是消息2", message -> {
            message.getMessageProperties().setExpiration("10000");
            System.out.println("成功发出消息1" + new Date());
            return message;
        });
        rabbitTemplate.convertAndSend("flower_sea_dlx_exchange", "routing-key-delay","我是消息1", message -> {
            message.getMessageProperties().setExpiration("5000");
            System.out.println("成功发出消息2" + new Date());
            return message;
        });
    }
}
