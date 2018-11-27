package com.alexander.springsocial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.reactor.core.ReactorCoreProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import reactor.core.publisher.Hooks;

@SpringCloudApplication
public class SpringSocialImagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialImagesApplication.class, args);
	}

	@Autowired
	protected void initialize(ReactorCoreProperties properties) {
		if (properties.getStacktraceMode().isEnabled()) {
			Hooks.onOperatorDebug();
		}
	}
}
