package com.daelim.yeondutalk.controller;

import com.daelim.yeondutalk.dto.JoinUserDTO;
import com.daelim.yeondutalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;



}
