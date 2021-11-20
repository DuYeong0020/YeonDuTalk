package com.daelim.yeondutalk.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;


    @Column(name = "MES_CNTS")
    @Lob
    private String contents;






}
