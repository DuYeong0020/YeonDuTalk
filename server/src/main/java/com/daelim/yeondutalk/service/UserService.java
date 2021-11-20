package com.daelim.yeondutalk.service;

import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.LogInUserDTO;
import com.daelim.yeondutalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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

            return null;
        }

    }

    // 로그인하기

    public Long logIn(LogInUserDTO logInUser){
        // 아이디가 맞는지 확인

        // 비밀번호가 맞는지 확인


        // 둘 다 맞으면


        return null;
    }


}
