package com.daelim.yeondutalk.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CMMN_CD")
public class CommonCode extends BaseEntity {

    @Id
    @Column(name = "CD_NM")
    private String name; // 코드명

    @Column(name = "CD_TYPE")
    private String type; // 코드유형

    @Column(name = "CD_DC")
    private String description; // 코드설명
}
