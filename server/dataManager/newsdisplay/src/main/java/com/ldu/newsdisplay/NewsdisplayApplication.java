package com.ldu.newsdisplay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.ldu")
@MapperScan(basePackages = {"com.ldu.newsdisplay.display.dao","com.ldu.newsdisplay.dataset.dao","com.ldu.newsdisplay.sysconfig.dao","com.ldu.newsdisplay.user.dao"})
public class NewsdisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsdisplayApplication.class, args);
    }

}
