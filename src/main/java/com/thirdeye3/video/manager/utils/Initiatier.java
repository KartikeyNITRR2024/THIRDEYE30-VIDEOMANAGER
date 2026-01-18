package com.thirdeye3.video.manager.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thirdeye3.video.manager.dtos.Response;

import jakarta.annotation.PostConstruct;

@Component
public class Initiatier {

    private static final Logger logger = LoggerFactory.getLogger(Initiatier.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${thirdeye.priority}")
    private Integer priority;

    @Value("${thirdeye.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() throws Exception {
        logger.info("Initializing Initiatier...");
        TimeUnit.SECONDS.sleep(priority * 3);
        logger.info("Initiatier initialized.");
    }

    public void refreshMemory() {
        logger.info("Going to refresh memory...");
        logger.info("Memory refreshed.");
    }
}
