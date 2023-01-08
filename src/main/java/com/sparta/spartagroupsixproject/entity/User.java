package com.sparta.spartagroupsixproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "users")
@Getter
//@NoArgsConstructor
@Builder
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

    /**
     *
     * @param userId
     * @return
     * superbuilder를 하면 무조건 기능이 된다
     * builder + NoArgs = x
     * builder + 기본생성자 = O
     */

    public boolean isValidUserId(Long userId) {
        return this.id.equals(userId);
    }
    public boolean isAdmin(){
        return this.userRoleEnum.equals(UserRoleEnum.ADMIN);
    }

}
