package com.connector.db.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Scheduler {

    @Scheduled(fixedRate = 2000)
    void scheduledJob() {
        log.info("Started job : {}", new Date());
    }
}
