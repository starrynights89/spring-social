package com.alexander.springsocial.chat;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Service
@EnableBinding(ChatServiceStreams.class)
public class InboundChatService extends AuthorizedWebSocketHandler {

    private final ChatServiceStreams chatServiceStreams;

    public InboundChatService(ChatServiceStreams chatServiceStreams) {
        this.chatServiceStreams = chatServiceStreams;
    }

    @Override
    protected Mono<Void> doHandle(WebSocketSession session) {
        return session
                .receive()
                .log(session.getId()
                    + "-inbound-incoming-chat-message")
                .map(WebSocketMessage::getPayloadAsText)
                .log(session.getId()
                    + "-inbound-convert-to-text")
                .flatMap(message ->
                        broadcast(message, session))
                .log(session.getId()
                    + "-inbound-broadcast-to-broker")
                .then();
    }

    public Mono<?> broadcast(String message, WebSocketSession user) {
        return user.getHandshakeInfo().getPrincipal()
                .map(principal -> chatServiceStreams.clientToBroker().send(
                        MessageBuilder
                        .withPayload(message)
                        .setHeader(ChatServiceStreams.USER_HEADER,
                                principal.getName())
                        .build()));
    }
}
