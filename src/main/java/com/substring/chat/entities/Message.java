package com.substring.chat.entities;

import java.time.LocalDateTime;

public class Message {

    private String sender;

    private String content;

    private LocalDateTime timeStamp;

    public Message() {

    }
    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
