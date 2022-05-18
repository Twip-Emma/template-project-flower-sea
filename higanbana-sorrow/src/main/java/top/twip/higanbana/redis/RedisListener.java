package top.twip.higanbana.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-18 11:26
 */
@Component
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("------------- " + new String(pattern) + "-------------");
        System.out.println(JSONObject.toJSONString(message, true));
        System.out.println("------------------------------------------------------");
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
        System.out.println("以上是过期的键，过期键监听无法监听值");
        System.out.println("------------------------------------------------------");
    }

}
