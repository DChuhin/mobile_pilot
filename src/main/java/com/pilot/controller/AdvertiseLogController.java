package com.pilot.controller;

import com.pilot.model.ChartEntry;
import com.pilot.model.dto.AdvertiseLogDTO;
import com.pilot.model.request.AdvertiseRequest;
import com.pilot.service.AdvertiseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Advertise log controller
 */
@RestController
public class AdvertiseLogController {

    @Autowired
    private AdvertiseLogService advertiseLogService;

    /**
     * Log advertise
     *
     * @param advertiseLogDTO advertise log dto
     * @return created log
     */
    @RequestMapping(value = "api/logs", method = RequestMethod.POST)
    public AdvertiseLogDTO postLog(@RequestBody AdvertiseLogDTO advertiseLogDTO) {
        return advertiseLogService.putLog(advertiseLogDTO);
    }

    /**
     * Get chart data set
     *
     * @param advertiseRequest advertise request
     * @return List
     */
    @RequestMapping(value = "api/logs/chart", method = RequestMethod.GET)
    public List<ChartEntry> getChartData(AdvertiseRequest advertiseRequest) {
        return advertiseLogService.getChartData(advertiseRequest);
    }

    /**
     * Get logs
     *
     * @param advertiseRequest advertise request
     * @return advertise logs
     */
    @RequestMapping(value = "api/logs", method = RequestMethod.GET)
    public List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest) {
        return advertiseLogService.getLogs(advertiseRequest);
    }

}
