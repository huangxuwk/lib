package com.mec.video.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MecVideoConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	public MecVideoConfig() {
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {HibernateConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {VideoWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
