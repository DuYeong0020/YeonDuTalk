package com.daelim.yeondutalk.domain;


import javax.persistence.*;

@Entity
@Table
public class RoomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
