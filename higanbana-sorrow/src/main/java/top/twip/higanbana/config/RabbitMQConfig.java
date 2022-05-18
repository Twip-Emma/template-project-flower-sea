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
    private  static final  String  DELAYED_EXCHANGE1 = "flower_sea_delayed_exchange_higanbana";
    private  static final  String  DELAYED_EXCHANGE2 = "flower_sea_delayed_exchange_cherry";
    //队列（需要监听的）
    private static  final  String  DELAYED_QUEUE1="flower_sea_delayed_queue_higanbana";
    private static  final  String  DELAYED_QUEUE2="flower_sea_delayed_queue_cherry";
    //routinKey
    private static  final  String ROUTINKEY1="flower_sea_routinkey_higanbana";
    private static  final  String ROUTINKEY2="flower_sea_routinkey_cherry";


    /**
     * 声明普通交换机
     */
    @Bean("delayedExchange1")
    CustomExchange delayedExchange1(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        /**
         * 交换机|类型|是否持久化|是否自动删除|其他参数
         */
        return new CustomExchange(DELAYED_EXCHANGE1,"x-delayed-message",true,false,map);
    }
    @Bean("delayedExchange2")
    CustomExchange delayedExchange2(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE2,"x-delayed-message",true,false,map);
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
    Binding queueBindingExcehang1(@Qualifier("delayedExchange1") CustomExchange delayedExchange, @Qualifier("delayedQueue1")  Queue delayedQueue){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(ROUTINKEY1).noargs();
    }
    @Bean
    Binding queueBindingExcehang2(@Qualifier("delayedExchange2") CustomExchange delayedExchange, @Qualifier("delayedQueue2")  Queue delayedQueue){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(ROUTINKEY2).noargs();
    }
}
