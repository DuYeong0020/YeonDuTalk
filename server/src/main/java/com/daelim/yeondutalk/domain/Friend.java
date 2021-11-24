package com.daelim.yeondutalk.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @AllArgsConstructor @Builder
@NoArgsConstructor
@Entity @Table @ToString
public class Friend extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRND_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQ_USER_ID")
    private User requestUser;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_USER_ID")
    private User tagUser;

    @Column(name = "ACPT_YN", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String accepted;


    @Override
    public void prePersist() {
        super.prePersist();
        if(accepted==null){
            accepted = "N";
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Friend friend = (Friend) o;
        return Objects.equals(getId(), friend.getId()) && Objects.equals(getRequestUser(), friend.getRequestUser()) && Objects.equals(getTagUser(), friend.getTagUser()) && Objects.equals(getAccepted(), friend.getAccepted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getRequestUser(), getTagUser(), getAccepted());
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }
}
