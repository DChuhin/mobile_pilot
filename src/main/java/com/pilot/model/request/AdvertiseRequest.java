package com.pilot.model.request;

import com.pilot.model.ChartMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Chart Request
 */
public class AdvertiseRequest {

    private ChartMode mode;

    private List<Long> channels = new ArrayList<>();

    private List<Long> advertises = new ArrayList<>();

    private Long startDate;

    private Long endDate;

    public ChartMode getMode() {
        return mode;
    }

    public void setMode(ChartMode mode) {
        this.mode = mode;
    }

    public List<Long> getChannels() {
        return channels;
    }

    public void setChannels(List<Long> channels) {
        this.channels = channels;
    }

    public List<Long> getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List<Long> advertises) {
        this.advertises = advertises;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
