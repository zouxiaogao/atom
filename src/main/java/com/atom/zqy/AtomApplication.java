package com.atom.zqy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.atom"})
public class AtomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomApplication.class, args);
    }

}
