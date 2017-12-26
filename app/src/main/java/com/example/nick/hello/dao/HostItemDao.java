package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 7/12/2560.
 */

public class HostItemDao {
    @SerializedName("url")      private String hostUrl;
    @SerializedName("port")     private String port;
    @SerializedName("hostname") private String hostname;

    public String getHostUrl() {
        return hostUrl;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setHostUrl(String host) {
        this.hostUrl = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
