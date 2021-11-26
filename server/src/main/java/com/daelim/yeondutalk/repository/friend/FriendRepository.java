package com.daelim.yeondutalk.repository.friend;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.friend.FriendListDTO;

import java.util.List;

public interface FriendRepository {

    // 친구 요청
    Long save(User requestUser, User tagUser);

    // 친구 수락(insert)
    Long saveContrary(User requestUser, User tagUser);

    // id로 값 찾기
    Friend findById(Long id);

    // requestUser와 tagUser로 친구 요청 데이터 가져오기
    Friend findByRequestTagId(User requestUser, User tagUser);

    // 친구 리스트 출력 User로 친구 리스트를 찾아오기

    List<FriendListDTO> findFriendList(User requestUser);

    // 친구 요청받은 리스트 출력
    List<FriendListDTO> findFriendListByTag(Long id);

}
