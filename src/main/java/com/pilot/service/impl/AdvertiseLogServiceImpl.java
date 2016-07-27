package com.pilot.service.impl;

import com.pilot.model.ChartContext;
import com.pilot.model.ChartEntry;
import com.pilot.model.dto.AdvertiseLogDTO;
import com.pilot.model.dto.builder.AdvertiseLogDTOBuilder;
import com.pilot.model.entity.AdvertiseLog;
import com.pilot.model.request.AdvertiseRequest;
import com.pilot.repository.AdvertiseLogDao;
import com.pilot.service.AdvertiseConsolidationService;
import com.pilot.service.AdvertiseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Advertise log service impl
 */
@Service
public class AdvertiseLogServiceImpl implements AdvertiseLogService {

    @Autowired
    private AdvertiseConsolidationService advertiseConsolidationService;

    @Autowired
    private AdvertiseLogDao advertiseLogDao;

    @Override
    @Transactional
    public AdvertiseLogDTO putLog(AdvertiseLogDTO advertiseLogDTO) {
        AdvertiseLog advertiseLog = new AdvertiseLog();
        advertiseLog.setAdvertiseId(advertiseLogDTO.getAdvertiseId());
        advertiseLog.setChannelId(advertiseLogDTO.getChannelId());
        advertiseLog.setDate(new Date().getTime());
        advertiseLogDao.saveOrUpdate(advertiseLog);
        return new AdvertiseLogDTOBuilder().build(advertiseLog);
    }

    @Override
    @Transactional
    public List<ChartEntry> getChartData(AdvertiseRequest advertiseRequest) {
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
    @Transactional
    public List<AdvertiseLogDTO> getLogs(AdvertiseRequest advertiseRequest) {
        List<AdvertiseLog> advertises = advertiseLogDao.getChartListByRequest(advertiseRequest);
        return advertises.stream()
                .map(advertiseLog -> new AdvertiseLogDTOBuilder().build(advertiseLog))
                .collect(Collectors.toList());
    }


}
