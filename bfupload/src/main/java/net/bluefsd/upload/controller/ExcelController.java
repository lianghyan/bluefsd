package net.bluefsd.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.bluefsd.upload.service.ExcelService;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class ExcelController {

	@Autowired
	ExcelService excelService;

	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean importResult(@RequestParam("file") MultipartFile file) throws Exception {

		String fileName = file.getOriginalFilename();
		ResultBean resultBean = excelService.batchImport(fileName, file);
		return resultBean;
	}
}
