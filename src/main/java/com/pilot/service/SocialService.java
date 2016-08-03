package com.pilot.service;

import com.pilot.service.model.dto.AdvertiseLogDTO;

/**
 * SocialService
 */
public interface SocialService {

    /**
     * Post advertise log to social
     *
     * @param advertiseLogDTO advertise log
     */
    void postAdvertiseLog(AdvertiseLogDTO advertiseLogDTO);

}
