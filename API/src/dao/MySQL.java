package dao;

import java.sql.Connection;

import com.horusframework.annotation.HorusConnectionParameters;
import com.horusframework.dao.HorusConnection;

public enum MySQL {
	@HorusConnectionParameters(driverName = "com.mysql.jdbc.Driver", jdbc = "mysql", host = "sjkap356", port = "3306", database = "test", user = "test", password = "E6D0AD7CAA8R")
	getInstance;

	public Connection getConnection() {
		try {
			return HorusConnection.getConnection(this.getDeclaringClass().getField("getInstance").getAnnotation(HorusConnectionParameters.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
