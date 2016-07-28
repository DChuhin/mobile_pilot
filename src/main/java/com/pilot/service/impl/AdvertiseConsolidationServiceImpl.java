package com.pilot.service.impl;

import com.pilot.service.AdvertiseConsolidationService;
import com.pilot.service.model.ChartEntry;
import com.pilot.service.model.ChartMode;
import com.pilot.service.model.ChartContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Advertise Consolidation Service Impl
 */
@Service
public class AdvertiseConsolidationServiceImpl implements AdvertiseConsolidationService {

    private static final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");

    @Override
    public List<ChartEntry> consolidateChartData(ChartContext context) {
        Map<LocalDateTime, ChartEntry> result = initEmptyMap(context);
        context.getAdvertiseLogList()
                .stream()
                .map(advertiseLog -> extractRoundedDate(context, advertiseLog.getDate()))
                .forEach(date -> result.get(date).addCount());
        return new ArrayList<>(result.values());
    }

    private Map<LocalDateTime, ChartEntry> initEmptyMap(ChartContext context) {
        ChronoUnit chronoUnit;
        DateTimeFormatter formatter;
        if (context.getMode() == ChartMode.DAY) {
            chronoUnit = ChronoUnit.DAYS;
            formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        } else {
            chronoUnit = ChronoUnit.HOURS;
            formatter = hourFormatter;
        }

        LocalDateTime start = extractRoundedDate(context, context.getStartDate());
        LocalDateTime end = extractRoundedDate(context, context.getEndDate());

        Map<LocalDateTime, ChartEntry> map = new TreeMap<>();
        while (start.compareTo(end) <= 0) {
            ChartEntry chartEntry = new ChartEntry();
            chartEntry.setLabel(start.format(formatter));
            map.put(start, chartEntry);
            start = start.plus(1, chronoUnit);
        }
        return map;
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
