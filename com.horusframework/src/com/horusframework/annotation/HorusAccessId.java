package com.horusframework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author miamarti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HorusAccessId {

	/**
	 * @return
	 */
	String value() default "";
}
