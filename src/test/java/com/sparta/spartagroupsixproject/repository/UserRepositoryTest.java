package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.entity.UserRoleEnum;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
class UserRepositoryTest {

    @Autowired // final은 Autowired 불가, mock은 when으로 값을 지정하기에 사용 x
    private UserRepository userRepository;

    @Test
    void findByUsername() {

        //given
        User user = User.builder().username("user1").password("1111").userRoleEnum(UserRoleEnum.USER).build();
        userRepository.save(user);
        //when
        User user1 = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지않는 사용자입니다"));
        //then
        Assertions.assertThat(user1).isEqualTo(user);
        Assertions.assertThat(user1.getUsername()).isEqualTo(user.getUsername());
        // test 환경에 대한 DB Test를 할때 DB 세팅을 별도로 - 실제 리포지토리에 연관 x가 되게게
       // CI/CD
        //
    }

    @Test
    void existsByUsername() {

    }
}
