package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;

public interface FriendRepository {

    // 친구 요청
    Long save(User requestUser, User tagUser);

    // 친구 수락(insert)
    Long saveContrary(User requestUser, User tagUser);

    // id로 값 찾기
    Friend findById(Long id);

    // requestUser와 tagUser로 친구 요청 데이터 가져오기
    Friend findByRequestTagId(User requestUser, User tagUser);


    // 친구 삭제 하기


    // 친구 요청온거 삭제하기


}
