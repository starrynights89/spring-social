package com.alexander.springsocial.comments;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Alexander Hartson
 */

@Service
@EnableBinding(Processor.class)
public class CommentService {

    private final CommentRepository repository;

    private final MeterRegistry meterRegistry;

    public CommentService(CommentRepository repository,
                          MeterRegistry meterRegistry) {
        this.repository = repository;
        this.meterRegistry = meterRegistry;
    }

    @StreamListener
    @Output(Processor.OUTPUT)
    public Flux<Comment> save(@Input(Processor.INPUT) Flux<Comment> newComments) {
        return repository
                .saveAll(newComments)
                .map(comment -> {
                    meterRegistry
                            .counter("comments.consumed", "imageId",
                                    comment.getImageId())
                            .increment();
                    return comment;
                });
    }

    @Bean
    CommandLineRunner setUp(CommentRepository repository) {
        return args -> {
            repository.deleteAll().subscribe();
        };
    }
}
