package com.pilot.service.impl;

import com.pilot.service.SocialService;
import com.pilot.service.model.dto.AdvertiseLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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

    private String facebookAccessToken;

    private final String FACEBOOK_PAGE_ID = "226076264455348";

    private final String MESSAGE_TEMPLATE = "Advertise logged: logId: %d, advertise: %d, channel: %d, device: %d";

    @PostConstruct
    public void init() {
        String twitterConsumerKey = environment.getProperty("spring.social.twitter.appId");
        String twitterConsumerSecret = environment.getProperty("spring.social.twitter.appSecret");
        String twitterAccessToken = environment.getProperty("spring.social.twitter.accessToken");
        String twitterAccessTokenSecret = environment.getProperty("spring.social.twitter.accessTokenSecret");
        twitter = new TwitterTemplate(twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessTokenSecret);
        String facebookAppId = environment.getProperty("spring.social.facebook.appId");
        facebookAccessToken = environment.getProperty("spring.social.facebook.pageToken");
        facebook = new FacebookTemplate(null, null, facebookAppId);
    }

    @Override
    public void postAdvertiseLog(AdvertiseLogDTO advertiseLogDTO) {
        String message = String.format(MESSAGE_TEMPLATE, advertiseLogDTO.getLogId(), advertiseLogDTO.getAdvertiseId(), advertiseLogDTO.getChannelId(), advertiseLogDTO.getDeviceId());
        new Thread(() -> twitter.timelineOperations().updateStatus(message)).start();
        new Thread(() -> {
            PagePostData pagePostData = new PagePostData(FACEBOOK_PAGE_ID).message(message);
            MultiValueMap<String, Object> map = pagePostData.toRequestParameters();
            map.add("access_token", facebookAccessToken);
            facebook.publish(FACEBOOK_PAGE_ID, "feed", map);
        }).start();

    }

}
