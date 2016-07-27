package com.pilot.model;


public class ChartEntry {

    private String label;

    private long count = 0;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * Add count
     */
    public void addCount() {
        count++;
    }
}
