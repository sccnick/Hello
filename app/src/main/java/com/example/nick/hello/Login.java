package com.example.nick.hello;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 9/11/2560.
 */

public class Login {
    private String date;
    public String isLogin;
    private String username;
    private String password;
    private int ids;

    private String image;
    private String state;
    private String name;
    private String status;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
