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
//	ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
//	System.out.println("开始配置comboPooledDataSource... ...");
//	try {
//		comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
//		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mec_blog_system");
//		comboPooledDataSource.setUser("root");
//		comboPooledDataSource.setPassword("31415926");
//		comboPooledDataSource.setInitialPoolSize(5);
//		comboPooledDataSource.setMaxPoolSize(20);
//		System.out.println("comboPooledDataSource配置完毕！");
//	} catch (PropertyVetoException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.mec.video");
		
		return sessionFactory;
	}
//	LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//	System.out.println("开始配置sessionFactoryBean... ...");
//	sessionFactoryBean.setDataSource(dataSource);
//	sessionFactoryBean.setPackagesToScan(new String[] { "com.mec.DAO", "com.mec.model" });
//	Properties properties = new Properties();
//	properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//	properties.setProperty("show_sql", "true");
//	sessionFactoryBean.setHibernateProperties(properties);
//	
//	System.out.println("sessionFactoryBean配置完毕！");
//	
//	return sessionFactoryBean;
	
	@Bean
	public Server getServer() {
		Server server = new Server();
		server.initNetConfig("net.cfg.properties");

		return server;
	}
	
}
