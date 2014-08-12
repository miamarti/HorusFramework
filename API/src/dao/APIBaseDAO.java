package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.horusframework.facade.HorusBaseDAOCRUDInterface;

/**
 * @author miamarti
 * 
 */
public class APIBaseDAO {

	/**
	 * Singleton implement
	 */
	public static final APIBaseDAO getInstance = new APIBaseDAO();

	private APIBaseDAO() {
	}

	/**
	 * @author miamarti
	 * 
	 */
	static protected class QueriesStandardsDB {

		/**
		 * Singleton implement
		 */
		public static final QueriesStandardsDB getInstance = new QueriesStandardsDB();

		private QueriesStandardsDB() {
		}

		/**
		 * @param request
		 * @param args
		 * @return
		 * @throws SQLException
		 */
		public int[] setRow(HttpServletRequest request, String... args) throws SQLException {
			int[] calback = { 0, 0 };
			calback = new int[2];

			PreparedStatement stmt = MySQL.getInstance.getConnection().prepareStatement("UPDATE `" + args[0] + "` SET `" + (request.getParameter("columnName") == "" ? " " : request.getParameter("columnName")) + "`='" + (request.getParameter("columnValue") == "" ? " " : request.getParameter("columnValue")) + "' WHERE `" + args[1] + "` = ?", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, args[2]);

			try {
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();
				while (rs.next()) {
					calback[1] = Integer.parseInt(rs.getString(1));
				}
				calback[0] = 1;
			} catch (Exception e) {
				e.printStackTrace();
				calback[0] = 0;
			}
			return calback;
		}

		/**
		 * @param id
		 * @param tabName
		 * @param colId
		 * @return
		 * @throws SQLException
		 */
		public Boolean delData(String id, String tabName, String colId) throws SQLException {
			PreparedStatement stmt = MySQL.getInstance.getConnection().prepareStatement("DELETE FROM `" + tabName + "` WHERE `" + colId + "` = ?");
			stmt.setString(1, id);
			try {
				stmt.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * @param id
		 * @param tabName
		 * @param colId
		 * @return
		 * @throws SQLException
		 */
		public ResultSet getId(String id, String tabName, String colId) throws SQLException {
			PreparedStatement stmt = MySQL.getInstance.getConnection().prepareStatement("SELECT * FROM `" + tabName + "` WHERE `" + colId + "` = ?");
			stmt.setString(1, id);
			return stmt.executeQuery();
		}

		/**
		 * @param colName
		 * @param value
		 * @param tabName
		 * @return
		 * @throws SQLException
		 */
		public ResultSet getByWhere(String colName, String value, String tabName) throws SQLException {
			PreparedStatement stmt = MySQL.getInstance.getConnection().prepareStatement("SELECT * FROM `" + tabName + "` WHERE `" + colName + "` = ?");
			stmt.setString(1, value);
			return stmt.executeQuery();
		}

		/**
		 * @param sqlQuery
		 * @return
		 * @throws SQLException
		 */
		public ResultSet getRLista(PreparedStatement stmt) throws SQLException {
			return stmt.executeQuery();
		}

		/**
		 * @param sqlQuery
		 * @return
		 * @throws SQLException
		 */
		public ResultSet getLista(String sqlQuery) throws SQLException {
			PreparedStatement stmt = MySQL.getInstance.getConnection().prepareStatement(sqlQuery);
			return stmt.executeQuery();
		}

		/**
		 * @param qrSql
		 * @return
		 * @throws SQLException
		 */
		public int[] setData(PreparedStatement stmt) throws SQLException {
			int[] calback = { 0, 0 };
			calback = new int[2];
			try {
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();
				while (rs.next()) {
					calback[1] = Integer.parseInt(rs.getString(1));
				}
				calback[0] = 1;
			} catch (Exception e) {
				calback[0] = 0;
				e.printStackTrace();
			}
			return calback;
		}
	}

	/**
	 * @author miamarti
	 * 
	 */
	public static class CustomersDAO extends QueriesStandardsDB implements HorusBaseDAOCRUDInterface {

		/**
		 * Singleton implement
		 */
		public static final CustomersDAO getInstance = new CustomersDAO();

		private CustomersDAO() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see dao.BaseDAOBean#getLista(java.lang.String[])
		 */
		public ResultSet getLista(String... args) throws SQLException {
			PreparedStatement stmt;
			if (args.length < 2) {
				stmt = MySQL.getInstance.getConnection().prepareStatement("SELECT * FROM customers;");
			} else {
				if (args.length > 2) {
					stmt = MySQL.getInstance.getConnection().prepareStatement("SELECT * FROM customers WHERE (`customer_id` LIKE ?) OR (`customer_name` LIKE ?) OR (`customer_fone` LIKE ?) LIMIT " + args[0] + " , " + args[1] + ";");
					stmt.setString(1, "%" + args[2] + "%");
					stmt.setString(2, "%" + args[2] + "%");
					stmt.setString(3, "%" + args[2] + "%");
				} else {
					stmt = MySQL.getInstance.getConnection().prepareStatement("SELECT * FROM customers LIMIT " + args[0] + " , " + args[1] + ";");
				}
			}
			return super.getRLista(stmt);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see dao.BaseDAOBean#setData(javax.servlet.http.HttpServletRequest,
		 * java.lang.String[])
		 */
		public int[] setData(HttpServletRequest request, String... args) throws SQLException {
			PreparedStatement stmt;
			if (args.length < 1) {
				stmt = MySQL.getInstance.getConnection().prepareStatement("INSERT INTO `customers` (`customer_name`,`customer_fone`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = MySQL.getInstance.getConnection().prepareStatement("UPDATE `customers` SET `customer_name` = ?, `customer_fone` = ? WHERE `customer_id` = ?", Statement.RETURN_GENERATED_KEYS);
				stmt.setString(3, args[0]);
			}
			stmt.setString(1, request.getParameter("customer_name"));
			stmt.setString(2, request.getParameter("customer_fone"));
			return super.setData(stmt);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see dao.BaseDAOBean#getId(java.lang.String)
		 */
		public ResultSet getId(String id) throws SQLException {
			return super.getId(id, "customers", "customer_id");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see dao.BaseDAOCRUDBean#getByWhere(java.lang.String,
		 * java.lang.String)
		 */
		public ResultSet getByWhere(String colName, String value) throws SQLException {
			return super.getByWhere(colName, value, "customers");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see dao.BaseDAOBean#delData(java.lang.String) TODO Validar antes de
		 * deletar Area
		 */
		public Boolean delData(String id) throws SQLException {
			return super.delData(id, "customers", "customer_id");
		}
	}
}
