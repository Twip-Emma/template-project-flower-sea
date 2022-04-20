package top.twip.cherry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.twip.cherry")
public class CherryBlossomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CherryBlossomsApplication.class, args);
    }

}
