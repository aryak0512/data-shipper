package com.connector.db.consumers;

import com.connector.db.domain.ChannelInfo;
import com.connector.db.domain.DatabaseChanelInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class DatabaseConsumer implements Consumer {

    private final ChannelInfo chanelInfo;

    public DatabaseConsumer(DatabaseChanelInfo chanelInfo) {
        this.chanelInfo = chanelInfo;
    }

    @Override
    public void consume() {
        DatabaseChanelInfo databaseChanelInfo = (DatabaseChanelInfo) chanelInfo;
        log.info("Database channel info : {}" ,databaseChanelInfo);
    }

    @Override
    public void run() {
        consume();
    }
}
