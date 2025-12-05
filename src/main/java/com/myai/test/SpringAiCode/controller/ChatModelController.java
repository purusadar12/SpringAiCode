package com.myai.test.SpringAiCode.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModelController {

    private ChatClient chatClient;

//    @Autowired
//    public ChatModelController(OpenAiChatModel model){
//        this.chatClient = ChatClient.create(model);
//    }

    @Autowired
    public ChatModelController(ChatClient.Builder builder){
        chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
                .build();
    }

    @GetMapping("/api/chat/{prompt}")
    public String chat(@PathVariable("prompt") String prompt){
        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    @GetMapping("/api/chat/metadata/{prompt}")
    public ResponseEntity<String> chatMetaData(@PathVariable("prompt") String prompt){
        ChatResponse chatResponse =  chatClient
                                        .prompt(prompt)
                                        .call()
                                        .chatResponse();

        System.out.println("Chatresponse.getmetadata(): " + chatResponse.getMetadata());

        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());

    }
}
