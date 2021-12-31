package com.gzmu.lpzyf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.gzmu.lpzyf.mapper"})
public class SubwayDataManageApp {
    public static void main(String[] args) {
        SpringApplication.run(SubwayDataManageApp.class,args);
    }
}
