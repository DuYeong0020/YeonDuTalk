package com.daelim.yeondutalk.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_USERS")
public class RoomUser extends BaseEntity {

    @EmbeddedId
    private RoomUserPK roomUserPK;




}
