package com.example.nick.hello.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerProcessDao {
    @SerializedName("Name")         private String name;
    @SerializedName("Mem_use")      private String memUse;
    @SerializedName("Cpu_use")      private String cpuUse;
    @SerializedName("Process_id")   private String processId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemUse() {
        return memUse;
    }

    public void setMemUse(String memUse) {
        this.memUse = memUse;
    }

    public String getCpuUse() {
        return cpuUse;
    }

    public void setCpuUse(String cpuUse) {
        this.cpuUse = cpuUse;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}

