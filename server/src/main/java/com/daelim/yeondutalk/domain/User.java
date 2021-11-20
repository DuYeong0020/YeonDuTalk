package com.daelim.yeondutalk.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor @ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id; // PK

    @Column(name = "LOGIN_ID", length = 50, nullable = false)
    private String userId; // 유저 id

    @Column(name = "USER_NAME", length = 50, nullable = false)
    private String userName; // 유저 이름

    @Column(name = "USER_PW", nullable = false)
    private String password; // 유저 비밀번호


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getUserName(), getPassword());
    }
}
