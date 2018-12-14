# tag::eureka
#!/usr/bin/env bash

# cf set-env spring-social-eureka-server spring.cloud.config.uri https://spring-social-config-server.cfapps.io
cf set-env spring-social-eureka-server spring.cloud.config.label production
# end::eureka

# tag::chat
cf set-env spring-social-chat spring.cloud.config.uri https://spring-social-config-server.cfapps.io
cf set-env spring-social-chat spring.cloud.config.label production

cf bind-service spring-social-chat spring-social-mongodb
cf set-env spring-social-chat spring.data.mongodb.uri \${vcap.services.spring-social-mongodb.credentials.uri}

cf bind-service spring-social-chat spring-social-rabbitmq
# end::chat

# tag::comments
cf set-env spring-social-comments spring.cloud.config.uri https://spring-social-config-server.cfapps.io
cf set-env spring-social-comments spring.cloud.config.label production

cf bind-service spring-social-comments spring-social-mongodb
cf set-env spring-social-comments spring.data.mongodb.uri \${vcap.services.spring-social-mongodb.credentials.uri}

cf bind-service spring-social-comments spring-social-rabbitmq
# end::comments

# tag::images
cf set-env spring-social-images spring.cloud.config.uri https://spring-social-config-server.cfapps.io
cf set-env spring-social-images spring.cloud.config.label production

cf bind-service spring-social-images spring-social-mongodb
cf set-env spring-social-images spring.data.mongodb.uri \${vcap.services.spring-social-mongodb.credentials.uri}

cf bind-service spring-social-images spring-social-rabbitmq
# end::images