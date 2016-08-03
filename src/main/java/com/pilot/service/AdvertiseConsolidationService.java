package com.pilot.service;

import com.pilot.service.model.ChartContext;
import com.pilot.service.model.ChartResponse;

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
    ChartResponse consolidateChartData(ChartContext context);

}
