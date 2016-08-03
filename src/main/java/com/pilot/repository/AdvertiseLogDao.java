package com.pilot.repository;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.repository.model.entity.AdvertiseLog;

import java.util.List;

/**
 * Advertise log dao
 */
public interface AdvertiseLogDao {

    /**
     * Save or update
     *
     * @param advertiseLog object
     */
    void saveOrUpdate(AdvertiseLog advertiseLog);

    /**
     * Delete
     *
     * @param advertiseLog object
     */
    void delete(AdvertiseLog advertiseLog);

    /**
     * Get
     *
     * @param id object id
     * @return AdvertiseLog
     */
    AdvertiseLog get(long id);

    /**
     * Get advertise logs
     *
     * @param advertiseRequest advertise request
     * @return advertise logs
     */
    List<AdvertiseLog> getChartListByRequest(AdvertiseRequest advertiseRequest);

}
