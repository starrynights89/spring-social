package com.alexander.springsocial.chat;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "lsb")
public class ChatConfigProperties {

    @Value("https://${vcap.application.uris[0]}")
    private String origin;
}
