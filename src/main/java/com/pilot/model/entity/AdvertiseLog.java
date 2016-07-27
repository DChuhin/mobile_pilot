package com.pilot.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Advertise log entity
 */
@Entity
@Table(name = "T_ADVERTISE_LOG")
public class AdvertiseLog implements Serializable {

    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "F_ADVERTISE_ID")
    private long advertiseId;

    @Column(name = "F_CHANNEL_ID")
    private long channelId;

    @Column(name = "F_DATE")
    private long date;

    @Column(name = "F_DEVICE_ID")
    private long deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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
}
