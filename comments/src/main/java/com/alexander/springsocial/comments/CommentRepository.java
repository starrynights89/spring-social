package com.alexander.springsocial.comments;

import com.alexander.springsocial.comments.Comment;
import org.springframework.data.repository.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alexander Hartson
 */

public interface CommentRepository extends Repository<Comment, String> {

    Flux<Comment> findByImageId(String imageId);

    Flux<Comment> saveAll(Flux<Comment> newComment);

    // Needed to support save()
    Mono<Comment> findById(String id);

    Mono<Void> deleteAll();
}
