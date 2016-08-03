package com.pilot.service.impl;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.repository.AdvertiseLogDao;
import com.pilot.repository.model.entity.AdvertiseLog;
import com.pilot.service.AdvertiseConsolidationService;
import com.pilot.service.AdvertiseLogService;
import com.pilot.service.model.ChartContext;
import com.pilot.service.model.ChartResponse;
import com.pilot.service.model.dto.AdvertiseLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Advertise log service impl
 */
@Service
@Transactional
public class AdvertiseLogServiceImpl implements AdvertiseLogService {

    private final AdvertiseConsolidationService advertiseConsolidationService;

    private final AdvertiseLogDao advertiseLogDao;

    @Autowired
    public AdvertiseLogServiceImpl(AdvertiseLogDao advertiseLogDao, AdvertiseConsolidationService advertiseConsolidationService) {
        this.advertiseLogDao = advertiseLogDao;
        this.advertiseConsolidationService = advertiseConsolidationService;
    }

    @Override
    public AdvertiseLogDTO putLog(AdvertiseLogDTO advertiseLogDTO) {
        AdvertiseLog advertiseLog = new AdvertiseLog();
        advertiseLog.setAdvertiseId(advertiseLogDTO.getAdvertiseId());
        advertiseLog.setChannelId(advertiseLogDTO.getChannelId());
        advertiseLog.setDate(new Date().getTime());
        advertiseLogDao.saveOrUpdate(advertiseLog);
        return AdvertiseLogDTO.newBuilder().build(advertiseLog);
    }

    @Override
    public ChartResponse getChartData(AdvertiseRequest advertiseRequest) {
        List<AdvertiseLog> advertises = advertiseLogDao.getChartListByRequest(advertiseRequest);
        ChartContext chartContext = new ChartContext(advertiseRequest);
        chartContext.setAdvertiseLogList(advertises);
        checkDates(chartContext);
        return advertiseConsolidationService.consolidateChartData(chartContext);
    }

    private void checkDates(ChartContext chartContext) {
        if (chartContext.getStartDate() == null) {
            long start = chartContext.getAdvertiseLogList()
                    .stream()
                    .min((adv1, adv2) -> adv1.getDate() - adv2.getDate() > 0 ? 1 : -1)
                    .get().getDate();
            chartContext.setStartDate(start);
        }
        if (chartContext.getEndDate() == null) {
            long end = chartContext.getAdvertiseLogList()
                    .stream()
                    .max((adv1, adv2) -> adv1.getDate() - adv2.getDate() > 0 ? 1 : -1)
                    .get().getDate();
            chartContext.setEndDate(end);
        }
    }

    @Override
    public List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest) {
        List<AdvertiseLog> advertises = advertiseLogDao.getChartListByRequest(advertiseRequest);
        return advertises.stream()
                .map(advertiseLog -> AdvertiseLogDTO.newBuilder().build(advertiseLog))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAdvertises() {
        return new ArrayList<>(advertiseLogDao.getChartListByRequest(new AdvertiseRequest())
                .stream()
                .map(AdvertiseLog::getAdvertiseId)
                .collect(Collectors.toSet()));
    }


}
