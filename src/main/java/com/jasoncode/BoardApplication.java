package com.jasoncode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BoardApplication extends ServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
