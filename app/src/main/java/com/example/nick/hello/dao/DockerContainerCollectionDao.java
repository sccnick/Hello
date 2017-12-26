package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerCollectionDao {
    @SerializedName("Host")     private List<DockerContainerListDao> host = new ArrayList<>();
    @SerializedName("Status")   private Boolean status;

    public List<DockerContainerListDao> getHost() {
        return host;
    }

    public void setHost(List<DockerContainerListDao> host) {
        this.host = host;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
