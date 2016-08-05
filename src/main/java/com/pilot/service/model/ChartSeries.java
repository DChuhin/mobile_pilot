package com.pilot.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Chart series
 */
public class ChartSeries {

    private String name;

    @JsonIgnore
    private Map<LocalDateTime, Long> counts;

    /**
     * Public constructor
     */
    public ChartSeries(List<LocalDateTime> xAxis) {
        counts = new LinkedHashMap<>();
        xAxis.forEach(date -> counts.put(date, 0L));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<LocalDateTime, Long> getCounts() {
        return counts;
    }

    /**
     * add count
     *
     * @param localDateTime log date
     */
    public void addCount(LocalDateTime localDateTime) {
        Long oldCount = counts.get(localDateTime);
        if (oldCount != null) {
            counts.replace(localDateTime, ++oldCount);
        } else {
            throw new IllegalArgumentException("wrong local date time for chart series");
        }
    }

    @JsonProperty("data")
    public List<Long> getChartSeriesData() {
        return ImmutableList.copyOf(counts.values());
    }

}
