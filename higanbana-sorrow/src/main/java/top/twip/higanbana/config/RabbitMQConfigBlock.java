package top.twip.higanbana.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-27 13:05
 */
@Configuration
public class RabbitMQConfigBlock {
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // 定义延时队列
    @Bean("delayProductQueue")
    public Queue delayQueue(){
        return QueueBuilder.durable("delayProductQueue")
                .withArgument("x-dead-letter-exchange", "flower_sea_dlx_queue")
                .withArgument("x-dead-letter-routing-key", "routing-key-delay")
                .build();
    }

    // 定义死信队列
    @Bean("dlxQueue")
    public Queue dlxQueue(){
        return QueueBuilder.durable("flower_sea_dlx_queue").build();
    }

    // 定义死信交换机
    @Bean("dlxExchange")
    public Exchange dlxExchange(){
        return ExchangeBuilder.directExchange("flower_sea_dlx_exchange").build();
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delay-type", "direct");
//        return ExchangeBuilder.directExchange("product-dlx-exchange","x-delayed-message",true,false,args).build();
    }


    //绑定队列和交换机
    @Bean("dlxBinding")
    public Binding dlxBinding(@Qualifier("dlxExchange")Exchange exchange,
                              @Qualifier("dlxQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("routing-key-delay").noargs();
    }
}
