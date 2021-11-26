package com.daelim.yeondutalk.controller;

import com.daelim.yeondutalk.argumentresolver.LogIn;
import com.daelim.yeondutalk.dto.ErrorResult;
import com.daelim.yeondutalk.dto.friend.FriendListDTO;
import com.daelim.yeondutalk.dto.user.UserDTO;
import com.daelim.yeondutalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {

        return new ErrorResult("400", e.getMessage());
    }
    // 친구 리스트
    @GetMapping("/yeondu/friends/{id}")
    public List<FriendListDTO> friendsList(@PathVariable Long id) {
        return friendService.getFriendList(id);
    }

    // 친구 아이디 값으로 친구 요청하기
    @PostMapping("/yeondu/friends/add")
    public Long friendRequest(@RequestBody String tagUserId, @LogIn UserDTO user) {
        return friendService.requestFriend(user.getId(), tagUserId);

    }


}
