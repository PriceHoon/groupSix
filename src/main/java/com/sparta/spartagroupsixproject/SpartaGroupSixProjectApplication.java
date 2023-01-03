package com.sparta.spartagroupsixproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//(scanBasePackages ={"com.sparta.spartagroupsixproject.service.BoardService"})
@SpringBootApplication
public class SpartaGroupSixProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaGroupSixProjectApplication.class, args);
    }

}
