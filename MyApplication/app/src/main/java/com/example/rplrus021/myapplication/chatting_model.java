package com.example.rplrus021.myapplication;

import java.util.Date;

public class chatting_model {
    private String image;
    private String text_chat;
    private String user;
    private long message_time;

    public chatting_model(String text_chat,String user){
        this.setText_chat(text_chat);
        this.setUser(user);

        message_time = new Date().getTime();
    }
    public chatting_model(){

    }

    public String getText_chat() {
        return text_chat;
    }

    public void setText_chat(String text_chat) {
        this.text_chat = text_chat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getMessage_time() {
        return message_time;
    }

    public void setMessage_time(long message_time) {
        this.message_time = message_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
