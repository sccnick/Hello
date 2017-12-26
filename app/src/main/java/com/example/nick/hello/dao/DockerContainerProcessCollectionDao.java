package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerProcessCollectionDao { @SerializedName("Host") private List<DockerContainerProcessDao> host = new ArrayList<>();

    public List<DockerContainerProcessDao> getHost() {
        return host;
    }

    public void setHost(List<DockerContainerProcessDao> host) {
        this.host = host;
    }
}
