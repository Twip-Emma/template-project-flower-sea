package top.twip.common.globalconfig;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import top.twip.common.constant.FeignConstants;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-24 16:16
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 设置一个请求头用于表示本次请求是由Feign调用
        requestTemplate.header(FeignConstants.HEADER_NAME.getValue(), FeignConstants.HEADER_VALUE.getValue());
    }
}
