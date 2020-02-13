package com.ojasa.reimburseit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {

	public String savefile(MultipartFile file) {
		byte[] bytes;
		try {
			bytes = file.getBytes();
			File file1 = new File(
					"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/output.pdf");
			OutputStream os = new FileOutputStream(file1);
			os.write(bytes);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
