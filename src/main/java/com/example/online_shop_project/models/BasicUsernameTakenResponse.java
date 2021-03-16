package com.example.online_shop_project.models;

public class BasicUsernameTakenResponse {

    private String message;

    public BasicUsernameTakenResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
