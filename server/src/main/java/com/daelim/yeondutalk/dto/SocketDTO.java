package com.daelim.yeondutalk.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SocketDTO {

    private String userName;
    private String content;
    private LocalDateTime dateTime;
}
