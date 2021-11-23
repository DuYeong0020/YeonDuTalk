package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.LogInUserDTO;

import java.util.Optional;

public interface UserRepository {

    public Long save(User user); // 유저를 저장하고 id반환

    public User findById(Long id); // id로 유저 찾고 User 반환

    public User findByUserId(String userId); // userId로 User 반환

    public User findByUserIdPassword(User userDTO); // password로 User 반환






}
