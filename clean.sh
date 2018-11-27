#!/usr/bin/env bash

cf delete -f spring-social-config-server &
cf delete -f spring-social-eureka-server &
cf delete -f spring-social-chat &
cf delete -f spring-social-comments &
cf delete -f spring-social-images &
cf delete -f spring-social-hystrix-dashboard &
