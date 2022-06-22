package top.twip.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 七画一只妖
 * @Date: 2022-06-22 15:14
 */
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取请求路径
        String reqUrlPath = request.getURI().getPath();

        // 获取ip地址
        String hostAddress = request.getRemoteAddress().getAddress().getHostAddress();

        // redis查询
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();

        if (ops.get(hostAddress) != null){
            ops.increment(hostAddress);
        }else {
            ops.set(hostAddress, 1, 10, TimeUnit.MINUTES);
        }

        Integer value = (Integer) ops.get(hostAddress);
        // 当判定为为脚本时
        if (value >= 10){
            ops.set(hostAddress, 9999);
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
