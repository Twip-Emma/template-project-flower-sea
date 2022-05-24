package top.twip.higanbana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 18:42
 */
@MapperScan("top.twip.higanbana.dao")
@EnableFeignClients(basePackages = "top.twip.common.feign")
@SpringBootApplication(scanBasePackages = {"top.twip.higanbana","top.twip.common.globalconfig"})
public class HiganbanaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiganbanaApplication.class, args);
    }
}
