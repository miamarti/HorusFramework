package dao;

import java.sql.Connection;
import org.horusframework.annotation.HorusConnectionParameters;
import org.horusframework.dao.HorusMySQLConnection;

/**
 * @author horusframework.org
 *
 */
public enum MySQL {
	@HorusConnectionParameters(host = "sjkap356", port = "3306", database = "sakila", user = "sakila", password = "PasswordHorn13245")
	getInstance;

	public Connection getConnection() {
		try {
			return HorusMySQLConnection.getConnection(this.getDeclaringClass().getField("getInstance").getAnnotation(HorusConnectionParameters.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}