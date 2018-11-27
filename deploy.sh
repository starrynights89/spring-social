#!/usr/bin/env bash

cf push spring-social-config-server -p config-server/build/libs/config-server-0.0.1-SNAPSHOT.jar &
cf push spring-social-eureka-server -p build/libs/spring-social-eureka-server-0.0.1-SNAPSHOT.jar &
cf push spring-social-chat -p chat/build/libs/chat-0.0.1-SNAPSHOT.jar &
cf push spring-social-comments -p comments/build/libs/comments-0.0.1-SNAPSHOT.jar &
cf push spring-social-images -p images/build/libs/images-0.0.1-SNAPSHOT.jar &
cf push spring-social-hystrix-dashboard -p hystrix-dashboard/build/libs/hystrix-dashboard-0.0.1-SNAPSHOT.jar &