package com.daelim.yeondutalk.service;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.friend.FriendListDTO;
import com.daelim.yeondutalk.repository.friend.FriendRepository;
import com.daelim.yeondutalk.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;



    // 친구 요청
    public Long requestFriend(Long requestId, Long tagUserId) {
        // User 객체(request, tag) 불러오기
        User requestUser = userRepository.findById(requestId);
        User tagUser = userRepository.findById(tagUserId);

        // 친구 요청하기
        Long saveFriendId = friendRepository.save(requestUser, tagUser);

        return saveFriendId;
    }

    // 친구 리스트 출력
    public List<FriendListDTO> getFriendList(Long requestId) {
        User requestUser = userRepository.findById(requestId);

        return friendRepository.findFriendListByRequestUser(requestUser);
    }

    // 친구 삭제
    public void deleteFriend(Long requestUserId, Long tagUserId) {

        User requestUser = userRepository.findById(requestUserId);
        User tagUser = userRepository.findById(tagUserId);

        friendRepository.deleteFriend(requestUser, tagUser);
    }

    // 친구 요청 삭제
    public void deleteFriendRequest(Long requestUserId, Long tagUserId) {
        User requestUser = userRepository.findById(requestUserId);
        User tagUser = userRepository.findById(tagUserId);
        friendRepository.deleteFriendRequest(requestUser, tagUser);
    }


    // 친구 요청 수락, 수락할 때 양방향 처리 해야함
    @Transactional
    public List<Long> acceptFriend(Long requestId, Long tagUserId){

        List<Long> friendIdList = new ArrayList<>();

        // User 객체(request, tag) 불러오기
        User requestUser = userRepository.findById(requestId);
        User tagUser = userRepository.findById(tagUserId);

        // 기존에 수락 요청한 Friend객체를 불러온다.
        Friend findFriend = friendRepository.findByRequestTagId(requestUser, tagUser);

        friendIdList.add(findFriend.getId());

        // 기존에 보낸 요청의 accept값을 N->Y으로 변경한다. dirtyChecking
        findFriend.setAccepted("Y");


        // 반대로 처리, insert로 처리 함
        Long saveContraryFriendId = friendRepository.saveContrary(requestUser, tagUser);

        friendIdList.add(saveContraryFriendId);

        return Collections.unmodifiableList(friendIdList);
    }



}
