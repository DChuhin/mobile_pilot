package com.pilot.model.dto.builder;

import com.pilot.model.dto.AdvertiseLogDTO;
import com.pilot.model.entity.AdvertiseLog;

/**
 * Advertise log dto builder
 */
public class AdvertiseLogDTOBuilder {

    private AdvertiseLogDTO advertiseLogDTO;

    /**
     * Create empty object
     */
    public AdvertiseLogDTOBuilder() {
        advertiseLogDTO = new AdvertiseLogDTO();
    }

    /**
     * Set channel
     *
     * @param value channel id
     * @return this
     */
    public AdvertiseLogDTOBuilder withChannelId(long value) {
        advertiseLogDTO.setChannelId(value);
        return this;
    }

    /**
     * Set log id
     *
     * @param value log id
     * @return this
     */
    public AdvertiseLogDTOBuilder withLogId(long value) {
        advertiseLogDTO.setLogId(value);
        return this;
    }

    /**
     * Set advertise id
     *
     * @param value advertise id
     * @return this
     */
    public AdvertiseLogDTOBuilder withAdvertiseId(long value) {
        advertiseLogDTO.setAdvertiseId(value);
        return this;
    }

    /**
     * Set date
     *
     * @param value date
     * @return this
     */
    public AdvertiseLogDTOBuilder withDate(long value) {
        advertiseLogDTO.setDate(value);
        return this;
    }

    /**
     * Set device
     *
     * @param value device id
     * @return this
     */
    public AdvertiseLogDTOBuilder withDevice(long value) {
        advertiseLogDTO.setDeviceId(value);
        return this;
    }

    /**
     * build dto
     *
     * @return AdvertiseLogDTO
     */
    public AdvertiseLogDTO build() {
        return advertiseLogDTO;
    }

    /**
     * build dto from entity
     *
     * @param advertiseLog advertise log
     * @return AdvertiseLogDTO
     */
    public AdvertiseLogDTO build(AdvertiseLog advertiseLog) {
        advertiseLogDTO.setChannelId(advertiseLog.getChannelId());
        advertiseLogDTO.setDate(advertiseLog.getDate());
        advertiseLogDTO.setAdvertiseId(advertiseLog.getAdvertiseId());
        advertiseLogDTO.setLogId(advertiseLog.getId());
        advertiseLogDTO.setDeviceId(advertiseLog.getDeviceId());
        return advertiseLogDTO;
    }

}
