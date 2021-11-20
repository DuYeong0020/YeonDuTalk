package com.daelim.yeondutalk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {


    @Id @Column(name = "ROOM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_CODE", nullable = false)
    private CommonCode code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRER_ID", nullable = false)
    private User user;



}
