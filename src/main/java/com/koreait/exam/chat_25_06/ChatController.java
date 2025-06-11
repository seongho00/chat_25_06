package com.koreait.exam.chat_25_06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/writeMessage")
    @ResponseBody
    public String writerMessage(@RequestParam("message") String message) {
        
        return "메세지 작성됨 : " + message;
    }

}
