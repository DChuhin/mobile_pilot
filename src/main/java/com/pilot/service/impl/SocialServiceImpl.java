package com.pilot.service.impl;

import com.pilot.service.SocialService;
import com.pilot.service.model.dto.AdvertiseLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Social service impl
 */
@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    private Environment environment;

    private Twitter twitter;

    private Facebook facebook;

    private final String MESSAGE_TEMPLATE = "Advertise logged: logId: %d, advertise: %d, channel: %d, device: %d";

    @PostConstruct
    public void init() {
        String twitterConsumerKey = environment.getProperty("spring.social.twitter.appId");
        String twitterConsumerSecret = environment.getProperty("spring.social.twitter.appSecret");
        String twitterAccessToken = environment.getProperty("spring.social.twitter.accessToken");
        String twitterAccessTokenSecret = environment.getProperty("spring.social.twitter.accessTokenSecret");
        twitter = new TwitterTemplate(twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);
        String facebookAppId = environment.getProperty("spring.social.facebook.appId");
        String facebookAccessToken = environment.getProperty("spring.social.facebook.appToken");
        facebook = new FacebookTemplate(facebookAccessToken, null, facebookAppId);
    }

    @Override
    public void postAdvertiseLog(AdvertiseLogDTO advertiseLogDTO) {
        String message = String.format(MESSAGE_TEMPLATE, advertiseLogDTO.getLogId(), advertiseLogDTO.getAdvertiseId(), advertiseLogDTO.getChannelId(), advertiseLogDTO.getDeviceId());
        new Thread(() -> twitter.timelineOperations().updateStatus(message)).start();
        new Thread(() -> facebook.feedOperations().updateStatus(message)).start();
    }

}
