package org.horusframework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author miamarti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HorusAttachment {

	/**
	 * @return
	 */
	String filename() default "";

	/**
	 * @return
	 */
	String contentType() default "";

	/**
	 * @return
	 */
	String extension() default "";

}
