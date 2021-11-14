package com.daelim.yeondutalk.domain;

import javax.persistence.*;

@Entity
@Table
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomCode;

}
