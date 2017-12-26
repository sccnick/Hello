package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 7/12/2560.
 */

public class AddHostCheckDao {
    @SerializedName("Status")   private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
