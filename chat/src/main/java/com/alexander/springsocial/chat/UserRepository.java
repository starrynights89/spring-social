package com.alexander.springsocial.chat;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Mono;

// tag::code[]
public interface UserRepository extends Repository<User, String> {

    Mono<User> findByUsername(String username);
}

