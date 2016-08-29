package com.pilot.service.model;

import com.pilot.repository.model.entity.AdvertiseLog;

import java.util.Objects;

public class DistinctAdvertiseKey {

    private long advertiseId;

    private long channelId;

    private DistinctAdvertiseKey(Builder builder) {
        advertiseId = builder.advertiseId;
        channelId = builder.channelId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getAdvertiseId() {
        return advertiseId;
    }

    public long getChannelId() {
        return channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DistinctAdvertiseKey)) return false;
        DistinctAdvertiseKey that = (DistinctAdvertiseKey) o;
        return advertiseId == that.advertiseId &&
                channelId == that.channelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(advertiseId, channelId);
    }

    public static final class Builder {
        private long advertiseId;
        private long channelId;

        private Builder() {
        }

        public Builder withAdvertiseId(long val) {
            advertiseId = val;
            return this;
        }

        public Builder withChannelId(long val) {
            channelId = val;
            return this;
        }

        public DistinctAdvertiseKey build() {
            return new DistinctAdvertiseKey(this);
        }

        public DistinctAdvertiseKey build(AdvertiseLog advertiseLog) {
            this.advertiseId = advertiseLog.getAdvertiseId();
            this.channelId = advertiseLog.getChannelId();
            return new DistinctAdvertiseKey(this);
        }

    }
}
