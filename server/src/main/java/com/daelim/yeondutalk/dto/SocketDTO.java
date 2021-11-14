package com.daelim.yeondutalk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class SocketDTO {

    private String userName;
    private String content;
}
