package com.koreait.exam.chat_25_06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public static record writeChatMessageResponse(long id) {

    }

    @GetMapping("/writeMessage")
    @ResponseBody
    public RsData writerMessage() {
        ChatMessage message = new ChatMessage("홍길동", "메세지");
        chatMessages.add(message);
        return new RsData(
                "S-1",
                "메세지 작성됨",
                new writeChatMessageResponse(message.getId())
        );
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData messages() {
        ChatMessage message = new ChatMessage("홍길동", "메세지");
        chatMessages.add(message);
        return new RsData(
                "S-1",
                "메세지 작성됨",
                chatMessages
        );
    }

}
