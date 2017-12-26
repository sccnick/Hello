package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerListDao {
    @SerializedName("Image")    private String image;
    @SerializedName("State")    private String state;
    @SerializedName("Name")     private String name;
    @SerializedName("Status")   private String status;

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
