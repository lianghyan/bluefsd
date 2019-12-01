package net.bluefsd.upload.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.bluefsd.model.StockPriceDetail;

public class ExcelUtil {

	public static XSSFWorkbook composeStock(Map<String, StockPriceDetail> detailMap) throws Exception {
		// OutputStream os = new FileOutputStream("c:\\temp\\aa.excel");
		XSSFWorkbook workbook = new XSSFWorkbook();

		Iterator<String> it = detailMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			StockPriceDetail detail = detailMap.get(key);
			XSSFSheet sheet = workbook.createSheet(detail.getIntervalType());
			int row = 0;
			appendStockSheet(sheet, row, detail);
		}

		return workbook;
	}

	public static XSSFWorkbook composeStockList(Map<String, List<StockPriceDetail>> detailMap) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		Iterator<String> it = detailMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			List<StockPriceDetail> details = detailMap.get(key);
			StockPriceDetail detail = details.get(0);
			XSSFSheet sheet = workbook.createSheet(detail.getIntervalType());
			int row = 0;
			for (int i = 0; i < details.size(); i++) {
				detail = details.get(i);
				row = appendStockSheet(sheet, row, detail);
			}
		}
		return workbook;
	}

	private static int appendStockSheet(XSSFSheet sheet, int row, StockPriceDetail detail) {
		// stockCd
		XSSFRow sheetRow = sheet.createRow(row++);
		sheetRow.createCell(0).setCellValue("StockCd");
		sheetRow.createCell(1).setCellValue(detail.getStockCd());
		// dates
		sheetRow = sheet.createRow(row++);
		sheetRow.createCell(0).setCellValue("dates");
		String[] dates = detail.getDates();
		for (int i = 0; i < dates.length; i++) {

			sheetRow.createCell(i + 1).setCellValue(dates[i]);
		}
		// values
		sheetRow = sheet.createRow(row++);
		sheetRow.createCell(0).setCellValue("values");
		double[] values = detail.getValues();
		for (int i = 0; i < values.length; i++) {
			sheetRow.createCell(i + 1).setCellValue(values[i]);
		}
		return row;
	}
}
