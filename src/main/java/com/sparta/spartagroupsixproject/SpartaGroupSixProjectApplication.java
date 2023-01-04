package com.sparta.spartagroupsixproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SpartaGroupSixProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaGroupSixProjectApplication.class, args);
    }

}
