package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity @Table
public class Friend extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRND_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQ_USER_ID")
    private User requestUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_USER_ID")
    private User tagUser;

    @Column(name = "ACPT_YN", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String accepted;



}
