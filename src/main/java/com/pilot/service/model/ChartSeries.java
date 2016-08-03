package com.pilot.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ChartSeries(Set<LocalDateTime> xAxis) {
        counts = new TreeMap<>();
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
        return new ArrayList<>(counts.values());
    }

}