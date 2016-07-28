package com.pilot.service.model.dto;


import com.pilot.repository.model.entity.AdvertiseLog;

/**
 * Advertise log dto
 */
public class AdvertiseLogDTO {

    private long logId;

    private long advertiseId;

    private long channelId;

    private long date;

    private long deviceId;

    private AdvertiseLogDTO(Builder builder) {
        logId = builder.logId;
        advertiseId = builder.advertiseId;
        channelId = builder.channelId;
        date = builder.date;
        deviceId = builder.deviceId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getLogId() {
        return logId;
    }

    public long getAdvertiseId() {
        return advertiseId;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getDate() {
        return date;
    }

    public long getDeviceId() {
        return deviceId;
    }


    public static final class Builder {
        private long logId;
        private long advertiseId;
        private long channelId;
        private long date;
        private long deviceId;

        private Builder() {
        }

        public Builder withLogId(long val) {
            logId = val;
            return this;
        }

        public Builder withAdvertiseId(long val) {
            advertiseId = val;
            return this;
        }

        public Builder withChannelId(long val) {
            channelId = val;
            return this;
        }

        public Builder withDate(long val) {
            date = val;
            return this;
        }

        public Builder withDeviceId(long val) {
            deviceId = val;
            return this;
        }

        public AdvertiseLogDTO build() {
            return new AdvertiseLogDTO(this);
        }

        public AdvertiseLogDTO build(AdvertiseLog advertiseLog) {
            advertiseId = advertiseLog.getAdvertiseId();
            channelId = advertiseLog.getChannelId();
            logId = advertiseLog.getId();
            date = advertiseLog.getDate();
            deviceId = advertiseLog.getDeviceId();
            return new AdvertiseLogDTO(this);
        }
    }
}
