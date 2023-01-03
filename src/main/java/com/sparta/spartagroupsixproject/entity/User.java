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

    @Column(nullable = false, unique = true)
    private String username; //최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성

    @Column(nullable = false)
    private String password; //최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum; //회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글 수정 / 삭제 가능

    public User(String username, String password, UserRoleEnum userRoleEnum) {
        this.username = username;
        this.password = password;
        this.userRoleEnum = userRoleEnum;
    }




    //각 역할에 맞는 Dto는 이름만 맞춰서 만들어 작업해주세요!
//    public User(SignUpRequestDto signUpDto) {
//        this.username = signUpDto.getUsername();
//        this.pwd = signUpDto.getPwd();
//        this.userRoleEnum = signUpDto.getUserRoleEnum();
//    }
}
