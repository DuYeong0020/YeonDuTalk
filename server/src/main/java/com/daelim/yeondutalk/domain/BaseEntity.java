package com.daelim.yeondutalk.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass @Getter
public class BaseEntity {

    private LocalDateTime createDate; // 생성일

    private LocalDateTime updateDate; // 수정일

    private boolean deleted; // 삭제여부
}
