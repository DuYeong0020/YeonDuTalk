package com.daelim.yeondutalk.service;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.repository.friend.FriendRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FriendServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendRepository friendRepository;

    @Test
    @DisplayName("친구 요청하기")
    public void requestFriend () throws Exception {
        // given

        User requestUser = User.builder().
                userId("requestId").userName("requestName").password("requestPassword")
                .build();

        User tagUser = User.builder().
                userId("tagId").userName("tagName").password("tagPassword")
                .build();

        Long saveRequestUserId = userService.joinUser(requestUser);
        Long saveTagUserId = userService.joinUser(tagUser);

        // when
        Long saveFriendId = friendService.requestFriend(saveRequestUserId, saveTagUserId);


        // then

        Friend findFriend = friendRepository.findById(saveFriendId);

        System.out.println("findFriend = " + findFriend);

        assertThat(findFriend.getRequestUser()).isSameAs(requestUser);
        assertThat(findFriend.getTagUser()).isSameAs(tagUser);

        assertThat(findFriend.getAccepted()).isEqualTo("N");

    }

    @Test
    @DisplayName("친구 추가 수락")
    public void acceptFriend () throws Exception {
        // given
        // 유저 두명 생성
        User requestUser = User.builder().
                userId("requestId").userName("requestName").password("requestPassword")
                .build();

        User tagUser = User.builder().
                userId("tagId").userName("tagName").password("tagPassword")
                .build();

        userService.joinUser(requestUser);
        userService.joinUser(tagUser);

        // 친구 요청
        Long saveId = friendRepository.save(requestUser, tagUser);

        // when
        friendService.acceptFriend(requestUser.getId(), tagUser.getId());

        em.flush();

        // then
        // 수락 요청 완료 된 Friend 객체
        Friend findFriend = friendRepository.findByRequestTagId(requestUser, tagUser);
        Friend findContraryFriend = friendRepository.findByRequestTagId(tagUser, requestUser);

        System.out.println("findFriend = " + findFriend);
        System.out.println("findContraryFriend = " + findContraryFriend);

        assertThat(findFriend.getRequestUser()).isSameAs(requestUser);
        assertThat(findFriend.getTagUser()).isSameAs(tagUser);
        assertThat(findFriend.getAccepted()).isEqualTo("Y");

        assertThat(findContraryFriend.getRequestUser()).isSameAs(tagUser);
        assertThat(findContraryFriend.getTagUser()).isSameAs(requestUser);
        assertThat(findContraryFriend.getAccepted()).isEqualTo("Y");
    }
}