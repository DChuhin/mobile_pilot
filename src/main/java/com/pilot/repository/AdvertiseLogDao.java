package com.pilot.repository;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.repository.model.entity.AdvertiseLog;

import java.util.List;

/**
 * Advertise log dao
 */
public interface AdvertiseLogDao extends GenericDao<AdvertiseLog, Long> {

    /**
     * Get advertise logs
     *
     * @param advertiseRequest advertise request
     * @return advertise logs
     */
    List<AdvertiseLog> getChartListByRequest(AdvertiseRequest advertiseRequest);

}
