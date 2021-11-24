package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class FriendMysqlRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;
    private Friend findFriend;

    @Test
    @DisplayName("친구 요청")
    public void friendRequest() throws Exception {
        // given
        User requestUser = User.builder().
                userId("requestId").userName("requestName").password("requestPassword")
                .build();

        User tagUser = User.builder().
                userId("tagId").userName("tagName").password("tagPassword")
                .build();
        userRepository.save(requestUser);
        userRepository.save(tagUser);
        em.flush();
        em.clear();

        Long saveId = friendRepository.save(requestUser, tagUser);

        Friend findFriend = friendRepository.findById(saveId);


        assertThat(findFriend).isNotNull();
        assertThat(findFriend.getRequestUser()).isSameAs(requestUser);
        assertThat(findFriend.getTagUser()).isSameAs(tagUser);
    }
    @Test
    @DisplayName("친구 요청을 했을 때 requestUser, tagUser로 Friend객체 찾아오기")
    public void findRequestTagId() throws Exception {
        // given
        // 유저 생성
        User requestUser = User.builder().
                userId("requestId").userName("requestName").password("requestPassword")
                .build();

        User tagUser = User.builder().
                userId("tagId").userName("tagName").password("tagPassword")
                .build();
        userRepository.save(requestUser);
        userRepository.save(tagUser);

        System.out.println("requestUser = " + requestUser);
        System.out.println("tagUser = " + tagUser);





        // when
        // 친구 요청함
        Long saveId = friendRepository.save(requestUser, tagUser);


        // then
        // 친구 요청한 객체 가져오기
        Friend findFriend = friendRepository.findByRequestTagId(requestUser, tagUser);
        System.out.println("findFriend = " + findFriend);

        assertThat(findFriend).isNotNull();
        assertThat(findFriend.getRequestUser()).isSameAs(requestUser);
        assertThat(findFriend.getTagUser()).isSameAs(tagUser);
        assertThat(findFriend.getAccepted()).isEqualTo("N");


    }





}