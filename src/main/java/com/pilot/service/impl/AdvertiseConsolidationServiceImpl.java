package com.pilot.service.impl;

import com.google.common.collect.ImmutableList;
import com.pilot.service.AdvertiseConsolidationService;
import com.pilot.service.model.ChartContext;
import com.pilot.service.model.ChartMode;
import com.pilot.service.model.ChartResponse;
import com.pilot.service.model.ChartSeries;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Advertise Consolidation Service Impl
 */
@Service
public class AdvertiseConsolidationServiceImpl implements AdvertiseConsolidationService {

    private static final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");

    @Override
    public ChartResponse consolidateChartData(ChartContext context) {
        Set<LocalDateTime> xAxis = initXAxis(context);
        Map<Long, ChartSeries> chartSeries = new HashMap<>();
        context.getAdvertiseLogList().forEach(advertiseLog -> {
            ChartSeries series = chartSeries.get(advertiseLog.getChannelId());
            if (series == null) {
                series = createNewSeries(xAxis, advertiseLog.getChannelId());
                chartSeries.put(advertiseLog.getChannelId(), series);
            }
            series.addCount(extractRoundedDate(context, advertiseLog.getDate()));
        });

        DateTimeFormatter formatter = context.getMode() == ChartMode.DAY ? DateTimeFormatter.ISO_DATE : hourFormatter;
        List<String> categories = xAxis
                .stream()
                .map(date -> date.format(formatter))
                .collect(Collectors.toList());

        ChartResponse chartResponse = new ChartResponse();
        chartResponse.setCategories(categories);
        chartResponse.setSeries(ImmutableList.copyOf(chartSeries.values()));
        return chartResponse;
    }

    private ChartSeries createNewSeries(Set<LocalDateTime> xAxis, long channelId) {
        ChartSeries chartSeries = new ChartSeries(xAxis);
        chartSeries.setName("" + channelId);
        return chartSeries;
    }

    private Set<LocalDateTime> initXAxis(ChartContext context) {
        ChronoUnit chronoUnit = context.getMode() == ChartMode.DAY ? ChronoUnit.DAYS : ChronoUnit.HOURS;
        LocalDateTime start = extractRoundedDate(context, context.getStartDate());
        LocalDateTime end = extractRoundedDate(context, context.getEndDate());

        Set<LocalDateTime> set = new TreeSet<>();
        while (start.compareTo(end) <= 0) {
            set.add(start);
            start = start.plus(1, chronoUnit);
        }
        return set;
    }

    private LocalDateTime extractRoundedDate(ChartContext context, long longDate) {
        LocalDateTime date = Instant.ofEpochMilli(longDate).atZone(ZoneId.of("UTC")).toLocalDateTime();
        if (context.getMode() == ChartMode.DAY) {
            return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else {
            return date.withMinute(0).withSecond(0).withNano(0);
        }
    }

}
