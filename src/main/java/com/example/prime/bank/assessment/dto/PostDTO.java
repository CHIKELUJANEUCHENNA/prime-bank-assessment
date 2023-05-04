package com.example.prime.bank.assessment.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String post_messages;
    private String post_Title;

    public PostDTO(Long id, String post_messages, String post_Title) {
        this.id = id;
        this.post_messages = post_messages;
        this.post_Title = post_Title;
    }
}
