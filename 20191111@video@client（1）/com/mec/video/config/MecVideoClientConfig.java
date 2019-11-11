package com.mec.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mec.net_framework.core.Client;

@ComponentScan
@Component
public class MecVideoClientConfig {

	public MecVideoClientConfig() {
	}

	@Bean
	public Client getClient() {
		Client client = new Client();
		client.initNetConfig("net.cfg.properties");
		
		return client;
	}
	
}
