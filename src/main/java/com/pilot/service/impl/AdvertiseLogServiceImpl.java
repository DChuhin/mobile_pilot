package com.pilot.service.impl;

import com.google.common.collect.Lists;
import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.repository.AdvertiseLogDao;
import com.pilot.repository.model.entity.AdvertiseLog;
import com.pilot.service.AdvertiseConsolidationService;
import com.pilot.service.AdvertiseLogService;
import com.pilot.service.SocialService;
import com.pilot.service.model.ChartContext;
import com.pilot.service.model.ChartResponse;
import com.pilot.service.model.DistinctAdvertiseKey;
import com.pilot.service.model.dto.AdvertiseLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Advertise log service impl
 */
@Service
@Transactional
public class AdvertiseLogServiceImpl implements AdvertiseLogService {

    private final AdvertiseConsolidationService advertiseConsolidationService;

    private final AdvertiseLogDao advertiseLogDao;

    private final SocialService socialService;

    @Autowired
    public AdvertiseLogServiceImpl(AdvertiseLogDao advertiseLogDao, AdvertiseConsolidationService advertiseConsolidationService, SocialService socialService) {
        this.advertiseLogDao = advertiseLogDao;
        this.advertiseConsolidationService = advertiseConsolidationService;
        this.socialService = socialService;
    }

    @Override
    public AdvertiseLogDTO putLog(AdvertiseLogDTO advertiseLogDTO) {
        AdvertiseLog advertiseLog = new AdvertiseLog();
        advertiseLog.setAdvertiseId(advertiseLogDTO.getAdvertiseId());
        advertiseLog.setChannelId(advertiseLogDTO.getChannelId());
        advertiseLog.setDate(new Date().getTime());
        advertiseLog.setSegment(advertiseLogDTO.getSegment());
        advertiseLog.setDeviceId(advertiseLogDTO.getDeviceId());
        advertiseLogDao.save(advertiseLog);
        socialService.postAdvertiseLog(AdvertiseLogDTO.newBuilder().build(advertiseLog));
        return AdvertiseLogDTO.newBuilder().build(advertiseLog);
    }

    @Override
    public ChartResponse getChartData(AdvertiseRequest advertiseRequest) {
        List<AdvertiseLog> advertises = advertiseLogDao.getChartListByRequest(advertiseRequest);
        List<AdvertiseLog> firstSegmentAds = filterFirstSegments(advertises);
        if (firstSegmentAds.isEmpty()) {
            return new ChartResponse();
        } else {
            ChartContext chartContext = new ChartContext(advertiseRequest);
            chartContext.setAdvertiseLogList(firstSegmentAds);
            processDates(chartContext);
            return advertiseConsolidationService.consolidateChartData(chartContext);
        }
    }

    private List<AdvertiseLog> filterFirstSegments(List<AdvertiseLog> advertises) {
        List<AdvertiseLog> firstSegmentAds = new ArrayList<>();
        Map<DistinctAdvertiseKey, Long> distinctLogs = new HashMap<>();
        for (AdvertiseLog advertiseLog : advertises) {
            DistinctAdvertiseKey distinctAdvertiseKey = DistinctAdvertiseKey.newBuilder().build(advertiseLog);
            Long previous = distinctLogs.get(distinctAdvertiseKey);
            if (previous == null) {
                distinctLogs.put(distinctAdvertiseKey, advertiseLog.getDate());
                firstSegmentAds.add(advertiseLog);
            } else {
                distinctLogs.replace(distinctAdvertiseKey, advertiseLog.getDate());
                if (advertiseLog.getDate() - previous > advertiseLog.getSegment() * 1000) {
                    firstSegmentAds.add(advertiseLog);
                }
            }
        }
        return firstSegmentAds;
    }

    private void processDates(ChartContext chartContext) {
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
        List<AdvertiseLog> advertises = Lists.reverse(advertiseLogDao.getChartListByRequest(advertiseRequest));
        return advertises.stream()
                .map(advertiseLog -> AdvertiseLogDTO.newBuilder().build(advertiseLog))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Long> getAdvertises() {
        return advertiseLogDao.getChartListByRequest(null)
                .stream()
                .map(AdvertiseLog::getAdvertiseId)
                .collect(Collectors.toSet());
    }


}
