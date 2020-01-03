package cn.liu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.liu.dao")
public class Cmfz180Application {

    public static void main(String[] args) {
        SpringApplication.run(Cmfz180Application.class, args);
    }

}
