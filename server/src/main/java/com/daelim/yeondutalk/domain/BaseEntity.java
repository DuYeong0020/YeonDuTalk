package com.daelim.yeondutalk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass @Getter
public class BaseEntity {

    @Column(name = "INST_DT")
    private LocalDateTime createDate; // 생성일

    @Column(name = "UPDT_DT")
    private LocalDateTime updateDate; // 수정일

    @Column(name = "DEL_DT")
    private boolean deleted; // 삭제여부
}
