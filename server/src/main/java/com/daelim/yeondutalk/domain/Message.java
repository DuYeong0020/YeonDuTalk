package com.daelim.yeondutalk.domain;


import javax.persistence.*;

@Entity @Table
public class Message {

    @Id @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;



    private String contents;






}
