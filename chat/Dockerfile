FROM alpine
MAINTAINER starrynights89
EXPOSE 8080
ADD build/libs/chat-0.0.1-SNAPSHOT.jar chat-0.0.1-SNAPSHOT.jar
RUN apk add --no-cache openjdk8
ENTRYPOINT ["java", "-jar", "chat-0.0.1-SNAPSHOT.jar"]