package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 7/12/2560.
 */

public class HostItemCollectionDao {
    @SerializedName("date")     private String date;
    @SerializedName("host")     private List<HostItemDao> host = new ArrayList<>();
    @SerializedName("status")   private Boolean status;
    @SerializedName("username") private String username;
    @SerializedName("password") private String password;
    @SerializedName("ids")      private int ids;

    public List<HostItemDao> getHost() {
        return host;
    }

    public int getIds() {
        return ids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void setHost(List<HostItemDao> host) {
        this.host = host;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
