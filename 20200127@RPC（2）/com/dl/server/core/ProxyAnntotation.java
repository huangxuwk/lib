package com.dl.server.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)

/**
 * ע��<br>
 * Ϊ��ɨ����׼����
 * 
 * @author dl
 *
 */
public @interface ProxyAnntotation {
	Class<?>[] interfaces();
}
