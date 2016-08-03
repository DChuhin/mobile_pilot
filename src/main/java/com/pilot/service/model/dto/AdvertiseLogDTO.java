package com.pilot.service.model.dto;


import com.pilot.repository.model.entity.AdvertiseLog;

/**
 * Advertise log dto
 */
public class AdvertiseLogDTO {

    private Long logId;

    private Long advertiseId;

    private Long channelId;

    private Long date;

    private Long deviceId;

    private AdvertiseLogDTO(Builder builder) {
        logId = builder.logId;
        advertiseId = builder.advertiseId;
        channelId = builder.channelId;
        date = builder.date;
        deviceId = builder.deviceId;
    }

    /**
     * Default constructor
     */
    public AdvertiseLogDTO() {
        /**
         * emtyp for swagger
         */
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getLogId() {
        return logId;
    }

    public Long getAdvertiseId() {
        return advertiseId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public Long getDate() {
        return date;
    }

    public Long getDeviceId() {
        return deviceId;
    }


    public static final class Builder {
        private Long logId;
        private Long advertiseId;
        private Long channelId;
        private Long date;
        private Long deviceId;

        private Builder() {
        }

        public Builder withLogId(Long val) {
            logId = val;
            return this;
        }

        public Builder withAdvertiseId(Long val) {
            advertiseId = val;
            return this;
        }

        public Builder withChannelId(Long val) {
            channelId = val;
            return this;
        }

        public Builder withDate(Long val) {
            date = val;
            return this;
        }

        public Builder withDeviceId(Long val) {
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
