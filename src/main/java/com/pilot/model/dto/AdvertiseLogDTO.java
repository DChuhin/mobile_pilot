package com.pilot.model.dto;

/**
 * Advertise log dto
 */
public class AdvertiseLogDTO {

    private long logId;

    private long advertiseId;

    private long channelId;

    private long date;

    private long deviceId;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getAdvertiseId() {
        return advertiseId;
    }

    public void setAdvertiseId(long advertiseId) {
        this.advertiseId = advertiseId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
