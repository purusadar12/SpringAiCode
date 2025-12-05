package com.myai.test.SpringAiCode.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringAiController {

    private OpenAiChatModel model;

    @Autowired
    public SpringAiController(OpenAiChatModel model){
        this.model = model;
    }

    @GetMapping("/api/ai/prompt/{prompt}")
    public String getPrompt(@PathVariable("prompt") String prompt){
        System.out.println("model" + model);
        return model.call(prompt);
    }
}
