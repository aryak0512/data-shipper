package com.connector.db.scheduler;

import com.connector.db.config.PropertyManager;
import com.connector.db.domain.FrequencyUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Configuration
@Slf4j
public class Job implements SchedulingConfigurer {

    private final PropertyManager propertyManager;

    public Job(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                log.info("Running Scheduler... {} ", Calendar.getInstance().getTime());
            }
        }, triggerContext -> {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());

            // resolve duration params from property file
            setNewExecutionTime(nextExecutionTime);
            return nextExecutionTime.toInstant();
        });
    }

    /**
     * load your execution time from database or property file
     */
    private void setNewExecutionTime(Calendar nextExecutionTime) {

        int duration = (int) propertyManager.getProperty("input.poll_duration");
        String unit = (String) propertyManager.getProperty("input.poll_duration_unit");

        log.info("Duration : {} |  Unit : {}", duration, unit);

        if ( FrequencyUnit.MINUTES.name().equals(unit.toUpperCase()) ) {
            nextExecutionTime.add(Calendar.MINUTE, duration);
        } else if ( FrequencyUnit.SECONDS.name().equals(unit.toUpperCase()) ) {
            nextExecutionTime.add(Calendar.SECOND, duration);
        } else if ( FrequencyUnit.HOURS.name().equals(unit.toUpperCase()) ) {
            nextExecutionTime.add(Calendar.HOUR, duration);
        } else {
            // default 1 second
            nextExecutionTime.add(Calendar.MILLISECOND, 1000);
        }
    }

}