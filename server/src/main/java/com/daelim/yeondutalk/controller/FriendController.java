package com.daelim.yeondutalk.controller;

import com.daelim.yeondutalk.argumentresolver.LogIn;
import com.daelim.yeondutalk.dto.ErrorResult;
import com.daelim.yeondutalk.dto.friend.FriendListDTO;
import com.daelim.yeondutalk.dto.user.DeleteUserDTO;
import com.daelim.yeondutalk.dto.user.RequestUserDTO;
import com.daelim.yeondutalk.dto.user.TagUserDTO;
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
    public Long friendRequest(@LogIn UserDTO user, @RequestBody TagUserDTO tagUserDTO) {


        return friendService.requestFriend(user.getId(), tagUserDTO.getTagUserId());

    }

    @PostMapping("/yeondu/friends/delete")
    public void deleteFriend(@LogIn UserDTO user, @RequestBody DeleteUserDTO deleteUserDTO) {
        friendService.deleteFriend(user.getId(), deleteUserDTO.getId());
    }

    @PostMapping("/yeondu/friends/accept")
    public List<Long> acceptFriend(@LogIn UserDTO user, @RequestBody RequestUserDTO requestUserDTO) {
        return friendService.acceptFriend(requestUserDTO.getId(), user.getId());
    }

    // 요청받은 친구 리스트 출력
    @GetMapping("yeondu/friends/requests/{id}")
    public List<FriendListDTO> requestFriendList(@LogIn UserDTO user, @PathVariable Long id) {
        return friendService.getRequestFriendList(user.getId());
    }


}
