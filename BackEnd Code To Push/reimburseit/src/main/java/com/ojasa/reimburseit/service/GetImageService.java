package com.ojasa.reimburseit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.Blob;
import com.ojasa.reimburseit.dao.GetImageDao;

@Service
public class GetImageService {

	@Autowired
	public GetImageDao getImageDao;

	public void fetchimage(int expReqId) {
		try {
			List<Integer> expDetailsList = new ArrayList<Integer>();
			expDetailsList = getImageDao.getExpDetailsId(expReqId);
			for (int i = 0; i < expDetailsList.size(); i++) {
				Blob photo = getImageDao.getBlobImage(expDetailsList.get(i));
				byte[] bytes = photo.getBytes(1, (int) photo.length());
				File file = new File(
						"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/invoice"
								+ i + ".pdf");
				OutputStream os = new FileOutputStream(file);
				os.write(bytes);
				os.close();
			}

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}
}

