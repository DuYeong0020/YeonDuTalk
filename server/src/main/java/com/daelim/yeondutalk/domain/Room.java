package com.daelim.yeondutalk.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor @ToString
public class Room extends BaseEntity {


    @Id @Column(name = "ROOM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_CODE", nullable = false)
    private CommonCode code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRER_ID", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Room room = (Room) o;
        return Objects.equals(getId(), room.getId()) && Objects.equals(getCode(), room.getCode()) && Objects.equals(getUser(), room.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getCode(), getUser());
    }
}
