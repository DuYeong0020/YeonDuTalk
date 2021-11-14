package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table @Entity @Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String user_id; // 유저 id

    private String password; // 유저 비밀번호

    private String user_name; // 유저 이름



}
