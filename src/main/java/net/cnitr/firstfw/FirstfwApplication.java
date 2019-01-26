package net.cnitr.firstfw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"net.cnitr.*.mapper"})
public class FirstfwApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstfwApplication.class, args);
    }

}

