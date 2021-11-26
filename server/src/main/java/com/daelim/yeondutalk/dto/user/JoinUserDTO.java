package com.daelim.yeondutalk.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @ToString
public class JoinUserDTO {


    private String userId;

    private String userName;

    private String userPassword;
}
