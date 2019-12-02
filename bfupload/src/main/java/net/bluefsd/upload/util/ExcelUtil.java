package net.bluefsd.upload.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.bluefsd.model.PriceDetail;
import net.bluefsd.model.SectorPriceDetail;
import net.bluefsd.model.StockPriceDetail;

public class ExcelUtil {

	public static XSSFWorkbook composeStock(Map<String,PriceDetail> detailMap)
			throws Exception {
		// OutputStream os = new FileOutputStream("c:\\temp\\aa.excel");
		XSSFWorkbook workbook = new XSSFWorkbook();

		Iterator<String> it = detailMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			PriceDetail detail = detailMap.get(key);
			XSSFSheet sheet = workbook.createSheet(detail.getIntervalType());
			int row = 0;
			appendStockSheet(sheet, row, detail);
		}

		return workbook;
	}

	public static XSSFWorkbook composeStockList(Map<String, List<PriceDetail>> detailMap) {
		XSSFWorkbook workbook = new XSSFWorkbook();

		Iterator<String> it = detailMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			List<PriceDetail> details = detailMap.get(key);
			PriceDetail detail = details.get(0);
			XSSFSheet sheet = workbook.createSheet(detail.getIntervalType());
			int row = 0;
			for (int i = 0; i < details.size(); i++) {
				detail = details.get(i);
				row = appendStockSheet(sheet, row, detail);
			}
		}
		return workbook;
	}

	private static int appendStockSheet(XSSFSheet sheet, int row, PriceDetail detail) {
		// stockCd
		XSSFRow sheetRow = sheet.createRow(row++);		
		
		if (detail instanceof SectorPriceDetail) {
			sheetRow.createCell(0).setCellValue("sectorCd");
			sheetRow.createCell(1).setCellValue(((SectorPriceDetail) detail).getSectorCd());
		} else {
			sheetRow.createCell(0).setCellValue("stockCd");
			sheetRow.createCell(1).setCellValue(((StockPriceDetail) detail).getStockCd());
		}
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
