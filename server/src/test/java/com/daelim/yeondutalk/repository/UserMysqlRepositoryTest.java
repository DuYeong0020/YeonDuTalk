package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class UserMysqlRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("유저를 저장한 후 영속성 컨텍스트를 지우고 유저를 찾은뒤 같은 내용인지를 비교")
    public void save() throws Exception {
        // given
        User buildUser = User.builder()
                .userId("saveId")
                .userName("dudu")
                .password("password").build(); // user 생성

        // when
        Long saveId = userRepository.save(buildUser); // user 저장

        em.clear(); // 영속성 컨텍스트를 지운다.

        User findUser = userRepository.findById(saveId); // 저장이 됬는지 user 꺼내기

        // then
        assertThat(findUser).isEqualTo(buildUser); // 저장한 user와 찾은 user 값 비교하기
    }

    @Test
    @DisplayName("이미 존재하는 아이디일 경우 객체를 리턴")
    public void findDuplicatedUser() throws Exception {
        // given
        User duplicatedUser = User.builder()
                .userId("duplicatedId")
                .userName("dudu")
                .password("password").build();

        Long saveId = userRepository.save(duplicatedUser); // 미리 유저 생성 후 저장하기

        // when
        User saveUser = User.builder()
                .userId("duplicatedId")
                .userName("dudu")
                .password("password").build(); // 전의 유저와 userId가 동일한 user 생성

        // then
        User findUser = userRepository.findByUserId(saveUser.getUserId()); // 중복된 user인지 확인. 참이면 유저 반환

        assertThat(findUser).isNotNull(); // NotNull인지 확인하기

    }

    @Test
    @DisplayName("중복되지 않은 아이디 입력시 null 반환")
    public void findNotDuplicatedUser() throws Exception {
        // given
        User saveUser = User.builder()
                .userId("notDuplicatedId")
                .userName("dudu")
                .password("password").build(); // 저장할 user 생성
        // when
        User findUser = userRepository.findByUserId(saveUser.getUserId()); // 중복되지 않으면 null 반환. false면 null 반환
        // then
        assertThat(findUser).isNull(); // Null인지 확인

    }

    @Test
    @DisplayName("올바른 아이디 비밀번호 입력시 객체 반환")
    public void findProperIdPassword() throws Exception {
        // given
        User saveUser = User.builder()
                .userId("userId")
                .userName("dudu")
                .password("password").build(); // 저장할 user 생성
        Long saveId = userRepository.save(saveUser);
        // when
        User user = User.builder()
                .userId("userId")
                .password("password").build();
        User findUser = userRepository.findByUserIdPassword(user);
        // then
        assertThat(findUser).isNotNull();

    }
    @Test
    @DisplayName("올바른 아이디 비밀번호 입력시 ")
    public void findNotProperIdPassword() throws Exception {
        // given
        User saveUser = User.builder()
                .userId("userId")
                .userName("dudu")
                .password("password").build(); // 저장할 user 생성
        Long saveId = userRepository.save(saveUser);
        // when
        User user = User.builder()
                .userId("NotProperUserId")
                .password("NotProperPassword").build();
        User findUser = userRepository.findByUserIdPassword(user);
        // then
        assertThat(findUser).isNull();

    }




}