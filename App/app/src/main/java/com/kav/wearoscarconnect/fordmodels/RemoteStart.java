
package com.kav.wearoscarconnect.fordmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteStart {

    @SerializedName("remoteStartDuration")
    @Expose
    private Integer remoteStartDuration;
    @SerializedName("remoteStartTime")
    @Expose
    private Integer remoteStartTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getRemoteStartDuration() {
        return remoteStartDuration;
    }

    public void setRemoteStartDuration(Integer remoteStartDuration) {
        this.remoteStartDuration = remoteStartDuration;
    }

    public Integer getRemoteStartTime() {
        return remoteStartTime;
    }

    public void setRemoteStartTime(Integer remoteStartTime) {
        this.remoteStartTime = remoteStartTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
