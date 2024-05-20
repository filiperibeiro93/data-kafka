package com.analytics.data.message;

import com.analytics.data.dto.CarPostDto;
import com.analytics.data.service.PostAnalyticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerMessage {

    private final PostAnalyticsService postAnalyticsService;

    @Autowired
    public KafkaConsumerMessage(PostAnalyticsService postAnalyticsService) {
        this.postAnalyticsService = postAnalyticsService;
    }

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-posts-group")
    public void listening(@Payload CarPostDto dto) {
        log.info("ANALYTICS DATA - Received Car Post Information: {}", dto);
        postAnalyticsService.saveDataAnalytics(dto);
    }
}
