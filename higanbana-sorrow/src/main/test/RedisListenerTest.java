import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.twip.higanbana.HiganbanaApplication;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-18 11:35
 */
@Slf4j
@SpringBootTest(classes = HiganbanaApplication.class)
public class RedisListenerTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void setHavingTimeKeyTest() {
        log.info("setHavingTimeKeyTest");

        //推入缓存
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("test1", "哼哼哼，啊啊啊啊啊啊啊啊啊", 5000, TimeUnit.MILLISECONDS);
    }
}
