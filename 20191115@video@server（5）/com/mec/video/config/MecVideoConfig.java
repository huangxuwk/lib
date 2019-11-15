package com.mec.video.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mec.net_framework.core.Server;

@ComponentScan
@Component
public class MecVideoConfig {
	
	public MecVideoConfig() {
	}
	
	@Bean
	public ComboPooledDataSource getDataSource() {
		return new ComboPooledDataSource();
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.mec.video");
		
		return sessionFactory;
	}
	
	@Bean
	public Server getServer() {
		Server server = new Server();
		server.initNetConfig("net.cfg.properties");

		return server;
	}
	
}
