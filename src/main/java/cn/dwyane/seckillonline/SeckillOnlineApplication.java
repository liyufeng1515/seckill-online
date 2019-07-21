package cn.dwyane.seckillonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.dwyane.seckillonline.model.dao")
public class SeckillOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillOnlineApplication.class, args);
    }

}
