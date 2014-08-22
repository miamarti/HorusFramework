package dao;

import java.sql.Connection;
import org.horusframework.annotation.HorusConnectionParameters;
import org.horusframework.dao.HorusOracleConnection;

/**
 * @author horusframework.org
 *
 */
public enum Oracle {
	@HorusConnectionParameters(host = "ora-data01", port = "1521", database = "data01", user = "ORA_USER", password = "PasswordHorn13245")
	getInstance;

	public Connection getConnection() {
		try {
			return HorusOracleConnection.getConnection(this.getDeclaringClass().getField("getInstance").getAnnotation(HorusConnectionParameters.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}