package com.koreait.exam.chat_25_06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeChatMessageResponse(long id, String authorName, String content) {

    }

    public record writeChatMessageRequest(String authorName, String content) {

    }

    @GetMapping("/room")
    public String showRoom() {
        return "chat/room";
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeChatMessageResponse> writerMessage(@RequestBody writeChatMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName, req.content);
        chatMessages.add(message);
        return new RsData(
                "S-1",
                "메세지 작성됨",
                new writeChatMessageResponse(message.getId(), message.getAuthorName(), message.getContent())
        );
    }

    public record messagesRequest(Long fromId) {

    }

    public record messagesResponse(List<ChatMessage> chatMessages, long count) {

    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<messagesResponse> messages(messagesRequest req) {

        List<ChatMessage> messages = chatMessages;

        log.debug("req : {}", req);
        // fromId : 몇 번 채팅로그부터 불러올 것인지 (다 불러올 수는 없기 떄문에)

        // fromId가 입력된 경우
        if (req.fromId != null) {
            // fromId가 전체 리스트의 몇번째 인덱스인지
            int index = IntStream.range(0, chatMessages.size())
                    .filter(i -> chatMessages.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            // 전체 리스트에서 찾아봤는데 인덱스가 없다면
            if (index != -1) {
                // fromId 인덱스부터 끝까지 List를 자른다.
                messages = chatMessages.subList(index + 1, chatMessages.size());
            }

        }


        return new RsData(
                "S-1",
                "메세지 작성됨",
                new messagesResponse(messages, messages.size())
        );
    }

}
