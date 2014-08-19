package com.horusframework.business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author miamarti
 *
 */
public class HorusToXLS {

	/**
	 * @author miamarti
	 *
	 */
	static public class getWriter {

		/**
		 * @param response
		 * @param rs
		 * @throws SQLException
		 */
		static public void write(HttpServletResponse response, ResultSet rs) {

			ResultSetMetaData rsMetaData;
			HSSFWorkbook workbook;
			HSSFSheet sheet;
			HSSFRow rowhead;
			HSSFRow row;
			String columnName;
			ServletOutputStream out;

			String sheetName = "Worksheet";
			String contentType = "application/vnd.ms-excel";
			String extension = "xls";

			try {
				rsMetaData = rs.getMetaData();
				workbook = new HSSFWorkbook();

				if (!rsMetaData.getTableName(1).equals("")) {
					sheetName = rsMetaData.getTableName(1);
				}

				sheet = workbook.createSheet(sheetName);
				rowhead = sheet.createRow((short) 0);

				int numberOfColumns = rsMetaData.getColumnCount();
				for (int i = 1; i <= numberOfColumns; i++) {
					columnName = rsMetaData.getColumnName(i);
					rowhead.createCell((i - 1)).setCellValue(columnName);
				}

				short line = 1;
				while (rs.next()) {
					row = sheet.createRow(line);
					for (int i = 1; i <= numberOfColumns; i++) {
						row.createCell((i - 1)).setCellValue(rs.getString(i));
					}
					line++;
				}

				response.setContentType(contentType);
				response.setHeader("Content-Disposition", "attachment; filename=" + sheetName + "." + extension);
				out = response.getOutputStream();
				workbook.write(out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
