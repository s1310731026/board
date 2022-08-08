package com.jasoncode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/");
        SpringApplication.run(BoardApplication.class, args);
    }

}
