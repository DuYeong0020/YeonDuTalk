package com.daelim.yeondutalk.domain;


import javax.persistence.*;

@Entity
@Table(name = "ROOM_USERS")
public class RoomUser extends BaseEntity {

    @EmbeddedId
    private RoomUserPK roomUserPK;




}
