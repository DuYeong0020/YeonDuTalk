package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity @Table
public class Friend extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID1")
    private User user1;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID2")
    private User user2;



}
