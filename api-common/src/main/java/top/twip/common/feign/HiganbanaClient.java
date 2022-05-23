package top.twip.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.twip.common.entity.user.UserInfo;
import top.twip.common.response.DataFactory;
import top.twip.common.response.SimpleData;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-23 21:26
 */
@FeignClient("higanbanaservice")
public interface HiganbanaClient {
    @PostMapping("/higanbana/getUserById")
    UserInfo getUserById(@RequestParam String id);
}
