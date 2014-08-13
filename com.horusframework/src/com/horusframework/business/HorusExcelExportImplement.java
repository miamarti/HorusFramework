package com.horusframework.business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.horusframework.annotation.HorusAttachment;
import com.horusframework.annotation.HorusBaseDAO;
import com.horusframework.facade.HorusBaseDAOCRUDInterface;
import com.horusframework.facade.HorusExcelInterface;

/**
 * @author miamarti
 *
 */
public class HorusExcelExportImplement implements HorusExcelInterface {
	private ResultSet rs;
	private ResultSetMetaData rsMetaData;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow rowhead;
	private HSSFRow row;
	private String columnName;
	private ServletOutputStream out;

	/**
	 * @param request
	 * @param response
	 * @param uri
	 * @param basedao
	 */
	private void getExcel(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {

		String sheetName = "Worksheet";
		String contentType = "application/vnd.ms-excel";
		String extension = "xls";

		try {
			if (uri.length > 4) {
				switch (uri[4]) {
				case "id":
					rs = basedao.getId(uri[5]);
					break;
				case "where":
					rs = basedao.getByWhere(uri[5], uri[6]);
					break;
				default:
					if (uri.length > 6) {
						rs = basedao.getLista(uri[4], uri[5], uri[6]);
					} else {
						rs = basedao.getLista(uri[4], uri[5]);
					}
					break;
				}
			} else {
				rs = basedao.getLista();
			}

			rsMetaData = rs.getMetaData();
			workbook = new HSSFWorkbook();

			if (this.getClass().isAnnotationPresent(HorusAttachment.class)) {
				sheetName = (this.getClass().getAnnotation(HorusAttachment.class)).filename();
				contentType = (this.getClass().getAnnotation(HorusAttachment.class)).contentType();
				extension = (this.getClass().getAnnotation(HorusAttachment.class)).extension();
			} else {
				if (!rsMetaData.getTableName(1).equals("")) {
					sheetName = rsMetaData.getTableName(1);
				}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.horusframework.facade.HorusExcelInterface#doGet(javax.servlet.http
	 * .HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String[])
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri) {
		HorusBaseDAOCRUDInterface basedao = null;
		try {
			basedao = (HorusBaseDAOCRUDInterface) ((this.getClass().getAnnotation(HorusBaseDAO.class)).value()).getField("getInstance").get("getInstance");
		} catch (Exception e) {
			e.getStackTrace();
		}
		getExcel(request, response, uri, basedao);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.horusframework.facade.HorusExcelInterface#doGet(javax.servlet.http
	 * .HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String[], com.horusframework.facade.HorusBaseDAOCRUDInterface)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response, String[] uri, HorusBaseDAOCRUDInterface basedao) {
		getExcel(request, response, uri, basedao);
	}

}
