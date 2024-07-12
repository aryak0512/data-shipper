package com.connector.db.tasks;

import com.connector.db.config.PropertyManager;
import com.connector.db.daemon.ScheduledTask;
import com.connector.db.domain.Channel;
import com.connector.db.domain.Direction;
import com.connector.db.exceptions.InvalidChannelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Task1 implements ScheduledTask {

    private final PropertyManager propertyManager;

    public Task1(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }

    @Override
    public void poll() {

        Channel channel = getChannel(Direction.INPUT);

        switch ( channel ) {
            case DATABASE -> log.info("Database is input channel");
            case QUEUE -> log.info("Queue is input channel");
            case REST_CALL -> log.info("Rest call is input channel");
            default -> throw new IllegalStateException("Unexpected value: " + channel);
        }

        // create the channel info of that channel

        // start threads of that consumer type and provide appropriate channelInfo

    }

    /**
     * figure out what kind of consumer or producer is needed
     * @return the channel
     */
    private Channel getChannel(Direction direction) {
        String type = direction.name();
        String ch = (String) propertyManager.getProperty(type.toLowerCase().concat(".channel"));
        Channel channel;
        try {
            channel = Channel.valueOf(ch.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Exception occurred while mapping channel : {}", e.getMessage(), e);
            throw new InvalidChannelException("Channel : " + ch + " does not exist!");
        }
        return channel;
    }
}
