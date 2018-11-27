package com.alexander.springsocial;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("images")
public class ImagesConfiguration {

    private String commentsUser;
    private String commentsPassword;

    public String getCommentsUser() {
        return commentsUser;
    }

    public void setCommentsUser(String commentsUser) {
        this.commentsUser = commentsUser;
    }

    public String getCommentsPassword() {
        return commentsPassword;
    }

    public void setCommentsPassword(String commentsPassword) {
        this.commentsPassword = commentsPassword;
    }
}
