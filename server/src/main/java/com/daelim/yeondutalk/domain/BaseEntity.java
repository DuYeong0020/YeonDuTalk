package com.daelim.yeondutalk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass @Getter
public class BaseEntity {

    @Column(name = "INST_DT", nullable = false, columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private LocalDateTime createDate; // 생성일

    @Column(name = "UPDT_DT")
    private LocalDateTime updateDate; // 수정일

    @Column(name = "DEL_DT", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String deleted; // 삭제여부

    @PrePersist
    public void prePersist(){
        createDate = LocalDateTime.now();
        deleted = "N";
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getCreateDate(), that.getCreateDate()) && Objects.equals(getUpdateDate(), that.getUpdateDate()) && Objects.equals(getDeleted(), that.getDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreateDate(), getUpdateDate(), getDeleted());
    }
}
