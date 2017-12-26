package com.example.nick.hello;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 23/11/2560.
 */

public class TestDocker {
    private List<Login> host = new ArrayList<>();
    private Boolean status;

    public List<Login> getHost() {
        return host;
    }

    public void setHost(List<Login> host) {
        this.host = host;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
