package com.pilot.service;

import com.pilot.service.model.ChartContext;
import com.pilot.service.model.ChartEntry;

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
