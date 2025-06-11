package com.koreait.exam.chat_25_06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    ChatMessage message = new ChatMessage("홍길동", "메세지");

    @GetMapping("/writeMessage")
    @ResponseBody
    public RsData writerMessage() {

        return new RsData("S-1", "메세지 작성됨", message);
    }

}
