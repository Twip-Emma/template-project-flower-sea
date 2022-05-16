package top.twip.higanbana.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-24 20:47
 */
@Configuration
public class RabbitMQConfig {
    //交换机（发送者需要投递到这里）
    private  static final  String  DELAYED_EXCHANGE = "flower_sea_delayed_exchange";
    //队列（需要监听的）
    private static  final  String  DELAYED_QUEUE1="flower_sea_delayed_queue_higanbana";
    private static  final  String  DELAYED_QUEUE2="flower_sea_delayed_queue_cherry";
    //routinKey
    private static  final  String ROUTINKEY="flower_sea_routinkey";


    /**
     * 声明普通交换机
     */
    @Bean("delayedExchange")
    CustomExchange delayedExchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        /**
         * 1.交换机
         * 2.类型
         * 3.是否持久化
         * 4.是否自动删除
         * 5其他参数
         */
        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true,false,map);
    }

    /**
     * 队列
     */
    @Bean("delayedQueue1")
    Queue delayedQueue1(){
        return new Queue(DELAYED_QUEUE1);
    }
    @Bean("delayedQueue2")
    Queue delayedQueue2(){
        return new Queue(DELAYED_QUEUE2);
    }

    /**
     * 绑定
     */
    @Bean
    Binding queueBindingExcehang1(@Qualifier("delayedExchange") CustomExchange delayedExchange, @Qualifier("delayedQueue1")  Queue delayedQueue){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(ROUTINKEY).noargs();
    }
    @Bean
    Binding queueBindingExcehang2(@Qualifier("delayedExchange") CustomExchange delayedExchange, @Qualifier("delayedQueue2")  Queue delayedQueue){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(ROUTINKEY).noargs();
    }
}
