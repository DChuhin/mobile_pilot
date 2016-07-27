package com.pilot.service;

import com.pilot.model.ChartContext;
import com.pilot.model.ChartEntry;

import java.util.List;

/**
 * Advertise Consolidation Service
 */
public interface AdvertiseConsolidationService {

    /**
     * Consolidate Chart Data
     *
     * @param context context
     * @return List
     */
    List<ChartEntry> consolidateChartData(ChartContext context);

}
