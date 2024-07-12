package com.connector.db.config;

import com.connector.db.domain.Channel;
import com.connector.db.domain.ChannelInfo;

public record InputChannel(

        Channel channel,
        int threads,
        int pollDuration,
        int pollUnit,
        ChannelInfo channelInfo

) {
}
