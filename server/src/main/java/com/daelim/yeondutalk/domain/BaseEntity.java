package com.daelim.yeondutalk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass @Getter
public class BaseEntity {

    @Column(name = "INST_DT", nullable = false, columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private LocalDateTime createDate; // 생성일

    @Column(name = "UPDT_DT")
    private LocalDateTime updateDate; // 수정일

    @Column(name = "DEL_DT", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String deleted; // 삭제여부
}
