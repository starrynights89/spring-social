FROM alpine
MAINTAINER starrynights89
EXPOSE 8888
ADD build/libs/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
RUN apk add --no-cache openjdk8
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]