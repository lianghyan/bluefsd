package net.bluefsd.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.bluefsd.comm.service.StockPriceService;
import net.bluefsd.model.StockPriceDetail;
import net.bluefsd.upload.service.ExcelService;
import net.bluefsd.upload.util.ExcelUtil;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class ExcelController {

	@Autowired
	ExcelService excelService;

	@Autowired
	StockPriceService stockPriceService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResultBean upload(MultipartFile[] files) throws Exception {

		String fileName = files[0].getOriginalFilename();
		ResultBean resultBean = excelService.batchImport(fileName, files[0]);
		return resultBean;
	}

	@RequestMapping(value = "/downloadtest", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(String stockCd) throws Exception {
		String filePath = "C:\\lydoc\\url.txt";
		FileSystemResource file = new FileSystemResource(filePath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		Map<String, StockPriceDetail> detailMap = stockPriceService.findStockPrice(stockCd);
		XSSFWorkbook workbook = ExcelUtil.composeStock(detailMap);
		workbook.write(new FileOutputStream(new File("c:\\temp\\aaa.xlsx")));

		return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));

	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public void downloadStock(HttpServletRequest request, HttpServletResponse response) {
		try {
			String stockCd = request.getParameter("stockCd");
			Map<String, StockPriceDetail> detailMap = stockPriceService.findStockPrice(stockCd);
			XSSFWorkbook workbook = ExcelUtil.composeStock(detailMap);

			String mimeType = "application/octet-stream";

			// set content attributes for the response
			response.setContentType(mimeType);

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "fsdexcel.xlsx");
			response.setHeader(headerKey, headerValue);
			workbook.write(response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = "/stockList", method = RequestMethod.GET)
	public void downloadStockList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] stockCds = request.getParameterValues("stockCd");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			Map<String, List<StockPriceDetail>> detailMap = stockPriceService.listStockPrice(stockCds, fromDate, toDate);
			XSSFWorkbook workbook = ExcelUtil.composeStockList(detailMap);

			String mimeType = "application/octet-stream";

			// set content attributes for the response
			response.setContentType(mimeType);

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "fsdexcel.xlsx");
			response.setHeader(headerKey, headerValue);
			workbook.write(response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
