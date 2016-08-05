package com.pilot.controller;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.service.AdvertiseLogService;
import com.pilot.service.model.ChartEntry;
import com.pilot.service.model.ChartResponse;
import com.pilot.service.model.dto.AdvertiseLogDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Advertise log controller
 */
@RestController
public class AdvertiseLogController {

    private final AdvertiseLogService advertiseLogService;

    @Autowired
    public AdvertiseLogController(AdvertiseLogService advertiseLogService) {
        this.advertiseLogService = advertiseLogService;
    }

    /**
     * Log advertise
     *
     * @param advertiseLogDTO advertise log dto
     * @return created log
     */
    @ApiOperation(value = "putLog", notes = "Create new advertise log", response = AdvertiseLogDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "advertiseId", value = "Advertise", required = true, dataType = "long"),
            @ApiImplicitParam(name = "channelId", value = "Channel", required = true, dataType = "long")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
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
    @ApiOperation(value = "getChartData", notes = "Get advertise logs and consolidate it in date periods", response = ChartEntry.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mode", value = "Period consolidation step", required = true, dataType = "string"),
            @ApiImplicitParam(name = "channels", value = "Channels", dataType = "long"),
            @ApiImplicitParam(name = "advertises", value = "Advertises", dataType = "long"),
            @ApiImplicitParam(name = "startDate", value = "Start date", dataType = "long"),
            @ApiImplicitParam(name = "endDate", value = "End date", dataType = "long")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ChartEntry.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "api/logs/chart", method = RequestMethod.GET)
    public ChartResponse getChartData(AdvertiseRequest advertiseRequest) {
        return advertiseLogService.getChartData(advertiseRequest);
    }

    /**
     * Get logs
     *
     * @param advertiseRequest advertise request
     * @return advertise logs
     */
    @ApiOperation(value = "getLogs", notes = "Get advertise logs", response = AdvertiseLogDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mode", value = "Period consolidation step", required = true, dataType = "string"),
            @ApiImplicitParam(name = "channels", value = "Channels", dataType = "long"),
            @ApiImplicitParam(name = "advertises", value = "Advertises", dataType = "long"),
            @ApiImplicitParam(name = "startDate", value = "Start date", dataType = "long"),
            @ApiImplicitParam(name = "endDate", value = "End date", dataType = "long")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AdvertiseLogDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "api/logs", method = RequestMethod.GET)
    public List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest) {
        return advertiseLogService.getLogs(advertiseRequest);
    }

    /**
     * Get advertises
     *
     * @return advertise ids
     */
    @ApiOperation(value = "getAdvertises", notes = "Get available advertise ids", response = AdvertiseLogDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Long.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "api/advertises", method = RequestMethod.GET)
    public Set<Long> getAdvertises() {
        return advertiseLogService.getAdvertises();
    }

}
