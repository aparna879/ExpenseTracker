package com.ojasa.reimburseit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ojasa.reimburseit.ocr.OcrImplementation;
import com.ojasa.reimburseit.service.OcrService;

@RestController
@RequestMapping("/ocr")
public class OcrController {
	
	@Autowired
	OcrService ocrservice;
	
	@PostMapping(value = "/scan" )
	public List<Map<String, String>> recFile(@RequestParam("fileToUpload[]") MultipartFile[] file)
	{
		Map<String, String> map=null;
		List<Map<String, String>> jsonList=new ArrayList<Map<String, String>>();

		for (int i = 0; i < file.length; i++) 
		{
			ocrservice.savefile(file[i]);
			map = OcrImplementation.scanBill(file[i]);
			jsonList.add(map);
		}
		return jsonList;
	}
}


