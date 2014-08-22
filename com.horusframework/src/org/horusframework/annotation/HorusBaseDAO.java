package org.horusframework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author miamarti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HorusBaseDAO {

	/**
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class value();

}
