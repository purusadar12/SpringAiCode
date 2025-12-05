package com.myai.test.SpringAiCode.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

    private ChatClient client;

    @Autowired
    public OllamaController(OllamaChatModel model){
        this.client =  ChatClient.create(model);
    }

//    @Autowired
//    public ChatModelController(ChatClient.Builder builder){
//        chatClient = builder
//                .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
//                .build();
//    }

    @GetMapping("/api/ollama/chat/{prompt}")
    public String chat(@PathVariable("prompt") String prompt){
        return client
                .prompt(prompt)
                .call()
                .content();
    }

}
