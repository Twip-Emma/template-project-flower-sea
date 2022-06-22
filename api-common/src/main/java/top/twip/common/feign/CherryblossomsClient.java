package top.twip.common.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 七画一只妖
 * @Date: 2022-06-22 9:32
 */
@FeignClient(value = "cherryservice")
public interface CherryblossomsClient {
}
