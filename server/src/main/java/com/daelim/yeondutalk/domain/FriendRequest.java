package com.daelim.yeondutalk.domain;

import javax.persistence.*;

@Entity @Table
public class FriendRequest extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_USER_ID")
    private User TagUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQ_USER_ID")
    private User RequestUser;


}
