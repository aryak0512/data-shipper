package com.connector.db.domain;

import lombok.Data;

@Data
public class DatabaseChanelInfo extends ChannelInfo {

    private DatabaseType databaseType;
    private String url;
    private String driver;
    private String username;
    private String password;
    private int connections;
    private String query;
}
