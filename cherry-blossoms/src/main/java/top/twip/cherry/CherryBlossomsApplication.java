package top.twip.cherry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("top.twip.cherry.dao")
@EnableFeignClients(basePackages = "top.twip.common.feign")
@SpringBootApplication(scanBasePackages = {"top.twip.cherry","top.twip.common.globalconfig"})
public class CherryBlossomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CherryBlossomsApplication.class, args);
    }

}
