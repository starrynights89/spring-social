FROM alpine
MAINTAINER starrynights89
EXPOSE 9000
ADD build/libs/comments-0.0.1-SNAPSHOT.jar comments-0.0.1-SNAPSHOT.jar
RUN apk add --no-cache openjdk8
ENTRYPOINT ["java", "-jar", "comments-0.0.1-SNAPSHOT.jar"]