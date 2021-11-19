package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id; // PK

    @Column(name = "LOGIN_ID", length = 50, nullable = false)
    private String user_id; // 유저 id

    @Column(name = "USER_NAME", length = 50, nullable = false)
    private String user_name; // 유저 이름

    @Column(name = "USER_PW", nullable = false)
    private String password; // 유저 비밀번호





}
