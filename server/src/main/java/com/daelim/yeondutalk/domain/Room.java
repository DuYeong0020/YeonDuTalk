package com.daelim.yeondutalk.domain;

import javax.persistence.*;

@Entity
@Table
public class Room extends BaseEntity {


    @Id @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_CODE")
    private CommonCode code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRER_ID")
    private User user;



}
