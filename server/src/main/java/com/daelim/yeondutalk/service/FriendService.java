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
            throw new RuntimeException("존재하지 않는 유저입니다.");
        } else if (tagUser == null) {
            throw new RuntimeException("존재 하지 않는 상대방입니다.");
        }

        // 친구인지 확인하기
        // 이미 친구라면 ?
        Friend findFriendByRequest = friendRepository.findByRequestTagId(requestUser, tagUser);
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("Y") && findFriendByRequest.getDeleted().equals("N")) {
            throw new RuntimeException("이미 친구로 등록되었습니다.");

        }
        // 아직 친구를 요청한 상태라면??? 상대방이 받지 않음
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("N") && findFriendByRequest.getDeleted().equals("N")) {
            throw new RuntimeException("아직 요청중입니다.");
        }
        Friend findFriendByTag = friendRepository.findByRequestTagId(tagUser, requestUser);
        // 한번 친구가 삭제가 된적이 있더라면 수정만
        if (findFriendByRequest != null && findFriendByRequest.getAccepted().equals("N") && findFriendByRequest.getDeleted().equals("Y")) {

            findFriendByRequest.setDeleted("N");


            findFriendByTag.setDeleted("Y");
            return findFriendByRequest.getId();
        }
        else if (findFriendByRequest == null) { // 양쪽에서 신청한적이 없다.
            return friendRepository.save(requestUser, tagUser);

        } else {
            throw new RuntimeException("친구 요청 에러");
        }



    }

    // 친구 리스트 출력
    public List<FriendListDTO> getFriendList(Long requestId) {
        User requestUser = userRepository.findById(requestId);

        return friendRepository.findFriendList(requestUser);
    }

    public List<FriendListDTO> getRequestFriendList(Long id) {
        return friendRepository.findFriendListByTag(id);
    }

    // 친구 삭제
    public void deleteFriend(Long requestUserId, Long tagUserId) {

        User requestUser = userRepository.findById(requestUserId);
        User tagUser = userRepository.findById(tagUserId);

        Friend findFriendByRequest = friendRepository.findByRequestTagId(requestUser, tagUser);
        Friend findFriendByTag = friendRepository.findByRequestTagId(tagUser, requestUser);
        if (findFriendByRequest != null && findFriendByTag != null) {
            findFriendByTag.setDeleted("Y");
            findFriendByRequest.setDeleted("Y");

            findFriendByTag.setAccepted("N");
            findFriendByRequest.setAccepted("N");
        }



    }




    // 친구 요청 수락, 수락할 때 양방향 처리 해야함
    @Transactional
    public List<Long> acceptFriend(Long requestId, Long tagUserId){

        List<Long> friendIdList = new ArrayList<>();


        // User 객체(request, tag) 불러오기
        User requestUser = userRepository.findById(requestId);
        User tagUser = userRepository.findById(tagUserId);

        // 기존에 수락 요청한 Friend객체를 불러온다.
        Friend findRequestFriend = friendRepository.findByRequestTagId(requestUser, tagUser);

        friendIdList.add(findRequestFriend.getId());

        // 기존에 보낸 요청의 accept값을 N->Y으로 변경한다. dirtyChecking
        findRequestFriend.setAccepted("Y");


        Friend findTagFriend = friendRepository.findByRequestTagId(tagUser, requestUser);
        if (findTagFriend != null) {// 이미 전에 친삭하여 데이터가 남아있는 경우
            findTagFriend.setAccepted("Y");
            findTagFriend.setDeleted("N");

            findRequestFriend.setAccepted("Y");
            findRequestFriend.setDeleted("N");
            friendIdList.add(findTagFriend.getId());

        }else{ // 처음 반대로 생성, insert로 처리 함
            Long saveContraryFriendId = friendRepository.saveContrary(requestUser, tagUser);

            friendIdList.add(saveContraryFriendId);
        }




        return Collections.unmodifiableList(friendIdList);
    }



}
