package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    //각 역할에 맞는 Dto는 이름만 맞춰서 만들어 작업해주세요!
//    public User(SignUpRequestDto signUpDto) {
//        this.username = signUpDto.getUsername();
//        this.pwd = signUpDto.getPwd();
//        this.userRoleEnum = signUpDto.getUserRoleEnum();
//    }
}
