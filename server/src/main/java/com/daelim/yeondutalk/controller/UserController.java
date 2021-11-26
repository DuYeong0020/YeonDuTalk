package com.daelim.yeondutalk.controller;

import com.daelim.yeondutalk.argumentresolver.LogIn;
import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.ErrorResult;
import com.daelim.yeondutalk.dto.user.JoinUserDTO;
import com.daelim.yeondutalk.dto.user.LogInUserDTO;
import com.daelim.yeondutalk.dto.user.UserDTO;
import com.daelim.yeondutalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {

        return new ErrorResult("400", e.getMessage());
    }


    // 회원가입
    @PostMapping("/yeondu/join")
    public Long join(@RequestBody JoinUserDTO userDTO){

        User joinUser = User.builder().password(passwordEncoder.encode(userDTO.getUserPassword()))
                .userName(userDTO.getUserName())
                .userId(userDTO.getUserId()).build();

        return userService.joinUser(joinUser);
    }

    // 로그인
    @PostMapping("/yeondu/login")
    public UserDTO logIn(@RequestBody LogInUserDTO userDTO, HttpServletRequest request) {
        User user = User.builder().userId(userDTO.getUserId()).password(userDTO.getUserPassword()).build();



        UserDTO logInUser = userService.logIn(user);
        HttpSession session = request.getSession();
        session.setAttribute("logInUser", logInUser);

        return logInUser;

    }

    @PostMapping("/yeondu/logout")
    public String logout(@LogIn UserDTO user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/";
    }



}
