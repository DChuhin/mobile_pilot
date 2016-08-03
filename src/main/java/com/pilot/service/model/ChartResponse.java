package com.pilot.service.model;

import java.util.List;

/**
 * Chart response
 */
public class ChartResponse {

    private List<String> categories;

    private List<ChartSeries> series;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<ChartSeries> getSeries() {
        return series;
    }

    public void setSeries(List<ChartSeries> series) {
        this.series = series;
    }
}
