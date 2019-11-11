package com.mec.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mec.net_framework.core.Server;

@ComponentScan
@Component
public class MecVideoConfig {
	
	public MecVideoConfig() {
	}
	
	@Bean
	public Server getServer() {
		Server server = new Server();
		server.initNetConfig("net.cfg.properties");

		return server;
	}
	
}
