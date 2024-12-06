package com.lss.hrbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.lss.hrbackend.domain.mapper"})
public class HrBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrBackendApplication.class, args);
    }

}
