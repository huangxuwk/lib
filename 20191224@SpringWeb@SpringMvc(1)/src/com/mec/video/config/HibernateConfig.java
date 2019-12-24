package com.mec.video.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class HibernateConfig {

	public HibernateConfig() {
	}

	@Bean
	public ComboPooledDataSource getDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.mec.video");
		
		return sessionFactory;
	}
	
	@Bean
	public PlatformTransactionManager getTransationManager(SessionFactory sessionFactory) {
		PlatformTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}
	
}
