#!/usr/bin/env bash

cf restart spring-social-config-server

sleep 15

cf restart spring-social-eureka-server &

sleep 15

cf restart spring-social-chat &

sleep 15

cf restart spring-social-images &

sleep 15

cf restart spring-social-comments &