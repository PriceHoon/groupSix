package com.sparta.spartagroupsixproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing // 해당 어노테이션을 사용시, 테스트에서 오류 발생, 따라서 테스트 코드에 해당 어노테이션을 mock형태로 선언해줘야한다.
//MockBean(JpaMetamodelMappingContext.class)를 컨트롤러TEST에 선언
public class SpartaGroupSixProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaGroupSixProjectApplication.class, args);
    }

}

