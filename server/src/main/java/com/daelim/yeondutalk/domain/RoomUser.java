package com.daelim.yeondutalk.domain;


import javax.persistence.*;

@Entity
@Table(name = "ROOM_USERS")
public class RoomUser extends BaseEntity {

    @Id
    @Column(name = "ROOM_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Room room;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;



}
