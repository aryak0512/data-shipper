package com.connector.db.config;

import com.connector.db.domain.Channel;

public record InputChannel(

        Channel channel,
        int threads,
        int pollDuration,
        int pollUnit

) {
}
