package com.pilot.model;

import com.pilot.model.entity.AdvertiseLog;
import com.pilot.model.request.AdvertiseRequest;

import java.util.List;

/**
 * Chart context
 */
public class ChartContext {

    private ChartMode mode;

    private List<AdvertiseLog> advertiseLogList;

    private Long startDate;

    private Long endDate;

    /**
     * public constructor
     *
     * @param advertiseRequest source request
     */
    public ChartContext(AdvertiseRequest advertiseRequest) {
        this.mode = advertiseRequest.getMode();
        this.startDate = advertiseRequest.getStartDate();
        this.endDate = advertiseRequest.getEndDate();
    }

    public ChartMode getMode() {
        return mode;
    }

    public void setMode(ChartMode mode) {
        this.mode = mode;
    }

    public List<AdvertiseLog> getAdvertiseLogList() {
        return advertiseLogList;
    }

    public void setAdvertiseLogList(List<AdvertiseLog> advertiseLogList) {
        this.advertiseLogList = advertiseLogList;
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
