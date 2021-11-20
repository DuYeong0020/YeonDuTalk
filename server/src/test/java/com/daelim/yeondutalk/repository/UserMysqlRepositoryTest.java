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



@SpringBootTest
class UserMysqlRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    @DisplayName("유저를 저장한 후 영속성 컨텍스트를 지우고 유저를 찾은뒤 같은 내용인지를 비교")
    public void save() throws Exception {
        // given: 유저를 생성
        User buildUser = User.builder()
                .userId("userid")
                .userName("dudu")
                .password("password").build();

        // when
        Long saveId = userRepository.save(buildUser);

        // 영속성 컨텍스트를 지운다.
        em.clear();

        User findUser = userRepository.findById(saveId);

        // then
        Assertions.assertThat(findUser).isEqualTo(buildUser);
    }



}