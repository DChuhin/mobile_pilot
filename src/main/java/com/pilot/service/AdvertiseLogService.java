package com.pilot.service;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.service.model.ChartResponse;
import com.pilot.service.model.dto.AdvertiseLogDTO;

import java.util.List;

/**
 * Advertise Log Service
 */
public interface AdvertiseLogService {

    /**
     * Create new log
     *
     * @param advertiseLogDTO advertise log dto
     * @return created AdvertiseLogDTO
     */
    AdvertiseLogDTO putLog(AdvertiseLogDTO advertiseLogDTO);

    /**
     * Get data for chart
     *
     * @param advertiseRequest request
     * @return Chart data set
     */
    ChartResponse getChartData(AdvertiseRequest advertiseRequest);

    /**
     * Get logs
     *
     * @param advertiseRequest request
     * @return logs DTOs
     */
    List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest);

    /**
     * Get available advertise ids
     *
     * @return List ids
     */
    List<Long> getAdvertises();
}
