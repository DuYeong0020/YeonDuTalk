package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table @Entity @Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id; // PK

    @Column(name = "LOGIN_ID")
    private String user_id; // 유저 id

    @Column(name = "USER_NAME")
    private String user_name; // 유저 이름

    @Column(name = "USER_PW")
    private String password; // 유저 비밀번호





}
