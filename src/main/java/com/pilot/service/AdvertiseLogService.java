package com.pilot.service;

import com.pilot.model.ChartEntry;
import com.pilot.model.dto.AdvertiseLogDTO;
import com.pilot.model.request.AdvertiseRequest;

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
    List<ChartEntry> getChartData(AdvertiseRequest advertiseRequest);

    /**
     * Get logs
     *
     * @param advertiseRequest request
     * @return logs DTOs
     */
    List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest);
}
