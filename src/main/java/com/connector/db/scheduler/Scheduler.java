package com.connector.db.scheduler;

import com.connector.db.daemon.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Scheduler {

    @Qualifier(value = "task1")
    ScheduledTask scheduledTask;

    public Scheduler(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }

    void scheduledJob() {
        log.info("Started job : {}", new Date());
        scheduledTask.poll();
        log.info("Finished job : {}", new Date());
    }
}
