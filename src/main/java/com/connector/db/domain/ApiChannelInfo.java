package com.connector.db.domain;

import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class ApiChannelInfo extends ChannelInfo {

    private DatabaseType databaseType;
    private String url;
    private HttpMethod method;
    private String username;
    private String password;
    private DataFormat dataFormat;
    private String apiKey;
}
