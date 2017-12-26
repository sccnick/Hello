package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 7/12/2560.
 */

public class SignUpCheckDao {
    @SerializedName("status")   private Boolean status;

    public Boolean getStatus() {
        return status;
    }
}
