package com.connector.db.domain;

import lombok.Data;

@Data
public class QueueChannelInfo extends ChannelInfo {

    private QueueType queueType;
    private String host;
    private int port;
    private String username;
    private String password;
    private String  queueName;
    private DataFormat dataFormat;
}
