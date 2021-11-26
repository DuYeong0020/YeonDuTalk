package com.daelim.yeondutalk.service;

import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.user.LogInUserDTO;
import com.daelim.yeondutalk.dto.user.UserDTO;
import com.daelim.yeondutalk.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public Long joinUser(User user) {
        String findUserId = user.getUserId();

        User findUser = userRepository.findByUserId(findUserId); // userId로 유저를 가져온다.

        if(findUser==null){  // 중복된 회원이 없으면

            return userRepository.save(user); // user를 저장

        }else { // 중복된 회원이 있으면 null 반환
            Map<String, Object> result = new HashMap<>();

            throw new RuntimeException("duplicate member");
        }

    }

    // 로그인하기

    public UserDTO logIn(User user){
        // 아이디가 맞는지 확인
        User findUserByUserId = userRepository.findByUserId(user.getUserId());
        // 비밀번호가 맞는지 확인
        User findUserByUserIdPassword = userRepository.findByUserIdPassword(user);
        // 둘 다 맞으면
        if (findUserByUserId == null && findUserByUserIdPassword == null) {
            throw new RuntimeException("NotUser");
        }
        return UserDTO.builder().id(findUserByUserIdPassword.getId())
                .name(findUserByUserIdPassword.getUserName()).build();

    }


}
