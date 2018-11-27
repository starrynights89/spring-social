package com.alexander.springsocial.chat;

import io.netty.handler.codec.socks.SocksAuthRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id private String id;
    private String username;
    private String password;
    private String[] roles;
}
