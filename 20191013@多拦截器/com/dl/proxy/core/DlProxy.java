package com.dl.proxy.core;

import com.hx.parser_reflect.util.PropertiesParser;

public class DlProxy extends BaseProxy {
	private static final String CGLIB_PROXY = "CGLib";
	private static final String JDK_PROXY = "JDK";
	
	private static String proxyType = CGLIB_PROXY;
	private Object object;
	private Interceptor interceptor;
	
	static {
		loadConfig("/proxy.cfg.properties");
	}
	
	public DlProxy() {
		interceptor = new Interceptor();
	}
	
	public DlProxy(Object object) {
		this();
		this.object = object;
	}
	
	public DlProxy(Interceptor interceptor) {
		this.interceptor = interceptor;
	}
	
	public DlProxy(Object object, Interceptor interceptor) {
		this.object = object;
		this.interceptor = interceptor;
	}

	public static void loadConfig(String configPath) {
		PropertiesParser.load(configPath);
		
		String configProxy = PropertiesParser.findElement("proxy_type");
		if (configProxy.length() > 0) {
			proxyType = configProxy;
		}
	}
	
	@Override
	Object getObject() {
		return null;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	Interceptor getInterceptor() {
		return interceptor;
	}
	
	@Override
	Object innerProxy(Class<?> klass, Object object) {
		if (object == null) {
			return null;
		}
		this.object = object;
		
		BaseProxy baseProxy = null;
		if (proxyType.equals("JDK")) {
			proxyType = JDK_PROXY;
		}
		if (proxyType.equals("CGLib")) {
			baseProxy = new CGLIBProxy(this);
		} else {
			baseProxy = new JDKProxy(this);
		}
		return baseProxy.getProxy(this.object.getClass());
	}
	
}
