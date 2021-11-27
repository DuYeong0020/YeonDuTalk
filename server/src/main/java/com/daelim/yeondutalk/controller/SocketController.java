package com.daelim.yeondutalk.controller;

import com.daelim.yeondutalk.dto.SocketDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Controller
public class SocketController {

    @MessageMapping("/receive")
    @SendTo("/send")
    public SocketDTO socketHandler(SocketDTO socketDTO) {
        String userName = socketDTO.getUserName();
        String content = socketDTO.getContent();
        LocalDateTime dateTime = socketDTO.getDateTime();

        SocketDTO result = new SocketDTO(userName, content, dateTime);

        return result;
    }
}
