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
@Transactional
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // 친구 요청
    public Long requestFriend(Long requestId, String tagUserId) {

        // User 객체(request, tag) 불러오기
        User tagUser = userRepository.findByUserId(tagUserId); // 상대방은 아이디 값으로 가져오기
        User requestUser = userRepository.findById(requestId); // 유저는 id값으로 가져오기


        if (requestUser == null) {
            throw new RuntimeException("notUser");
        } else if (tagUser == null) {
            throw new RuntimeException("notTagUser");
        }

        // 친구인지 확인하기
        // 이미 친구라면 ?
        Friend findFriendByRequest = friendRepository.findByRequestTagId(requestUser, tagUser);
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("Y") && findFriendByRequest.getDeleted().equals("N")) {
            throw new RuntimeException("alreadyFriends");

        }
        // 아직 친구를 요청한 상태라면??? 상대방이 받지 않음
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("N") && findFriendByRequest.getDeleted().equals("N")) {
            throw new RuntimeException("alreadyFriendsRequest");
        }
        Friend findFriendByTag = friendRepository.findByRequestTagId(tagUser, requestUser);
        // 한번 친구가 삭제가 된적이 있더라면 수정만
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("N") && findFriendByRequest.getDeleted().equals("Y")) {

            findFriendByRequest.setDeleted("N");


            findFriendByTag.setDeleted("N");
            return findFriendByRequest.getId();
        }
        else if (findFriendByRequest == null) { // 양쪽에서 신청한적이 없다.
            Long saveFriendId = friendRepository.save(requestUser, tagUser);
            return saveFriendId;
        } else {
            throw new RuntimeException("friendsRequestError");
        }



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
