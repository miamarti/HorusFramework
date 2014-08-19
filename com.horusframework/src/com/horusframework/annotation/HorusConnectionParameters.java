package com.horusframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author miamarti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface HorusConnectionParameters {

	/**
	 * @return
	 */
	String database() default "test";

	/**
	 * @return
	 */
	String user() default "root";

	/**
	 * @return
	 */
	String password() default "";

	/**
	 * @return
	 */
	String host() default "localhost";

	/**
	 * @return
	 */
	String language() default "pt";

	/**
	 * @return
	 */
	String country() default "BR";

	/**
	 * @return
	 */
	String port() default "";

	/**
	 * @return
	 */
	boolean autoCommit() default true;
}
