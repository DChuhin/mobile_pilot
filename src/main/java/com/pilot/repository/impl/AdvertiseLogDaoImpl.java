package com.pilot.repository.impl;

import com.pilot.controller.model.request.AdvertiseRequest;
import com.pilot.repository.AdvertiseLogDao;
import com.pilot.repository.model.entity.AdvertiseLog;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Advertise log dao impl
 */
@Repository
public class AdvertiseLogDaoImpl extends AbstractGenericDaoImpl<AdvertiseLog, Long> implements AdvertiseLogDao {

    @Override
    public Class<AdvertiseLog> getPersistentClass() {
        return AdvertiseLog.class;
    }

    @Override
    public List<AdvertiseLog> getChartListByRequest(AdvertiseRequest advertiseRequest) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());

        List<Long> advertises = advertiseRequest.getAdvertises();
        List channels = advertiseRequest.getChannels();

        if (advertises != null && !advertises.isEmpty()) {
            criteria.add(Restrictions.in("advertiseId", advertises));
        }

        if (channels != null && !channels.isEmpty()) {
            criteria.add(Restrictions.in("channelId", channels));
        }

        if (advertiseRequest.getStartDate() != null) {
            criteria.add(Restrictions.ge("date", advertiseRequest.getStartDate()));
        }

        if (advertiseRequest.getEndDate() != null) {
            criteria.add(Restrictions.le("date", advertiseRequest.getEndDate()));
        }

        return find(criteria);
    }
}
