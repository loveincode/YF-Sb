package com.loveincode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author huyifan
 * @date :2019-05-23
 * PACKAGE_NAME
 */
@SpringBootApplication
@EnableFeignClients
public class YFWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YFWebApplication.class, args);
    }
}
