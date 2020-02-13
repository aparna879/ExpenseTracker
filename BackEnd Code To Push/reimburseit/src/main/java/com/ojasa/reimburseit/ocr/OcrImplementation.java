package com.ojasa.reimburseit.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class OcrImplementation {
	static String getDate(String line) {
		StringBuilder str = new StringBuilder();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(line);
		while (m.find()) {
			str.append(m.group());
			str.append("-");
		}
		return str.toString().substring(0, str.toString().length() - 1);
	}

	static String getTotal(String str) {
		int loc = str.indexOf('L');
		String total = str.substring(loc + 1);
		return total.strip();
	}

	static String expenseName(String line) {
		return (line.replaceAll("[0123456789.]", "")).strip();
	}

	public static Map<String, String> scanBill(MultipartFile multipartfile) {

		String date = "";
		String total = "";
		String expName = "";
		Map<String, String> map = new HashMap<>();

		ITesseract tess = new Tesseract();
		String str;
		try {
			str = tess.doOCR(new File(
					"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/output.pdf"));
			FileWriter writer = new FileWriter(
					"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/output.txt",
					false);
			writer.write(str);
			writer.close();

			File file = new File(
					"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/output.txt"); // creates
																																					// a
																																					// new
																																					// file
																																					// instance
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					if (line.contains("Date")) {
						date = getDate(line);
						expName = expenseName(br.readLine());
					}
					if (line.contains("TOTAL")) {
						total = getTotal(line);
					}
				}
			}
			fr.close();
			br.close();

			map.put("expensename", expName);
			map.put("expensecost", total);
			map.put("expensedate", date);

		} catch (Exception e) {
			e.getMessage();
		}

		return map;
	}
}
