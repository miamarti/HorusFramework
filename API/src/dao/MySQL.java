package dao;

import java.sql.Connection;

import com.horusframework.annotation.HorusConnectionParameters;
import com.horusframework.dao.HorusMySQLConnection;

public enum MySQL {
	@HorusConnectionParameters(host = "localhost", port = "3306", database = "sakila", user = "sakila", password = "PasswordHorn13245")
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
