package org.kolokolov.simpleproject.service;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.dao.FileDAO;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private FileDAO fileDAO;

	public EmployeeFile getFile(String fileId) {
		return fileDAO.getFile(Integer.parseInt(fileId));
	}
	
	public void saveFile(EmployeeFile file) {
		logger.debug("Saving file: " + file.getName() + ", " + Arrays.toString(file.getData()) + ", " + file.getEmployee());
		fileDAO.saveFile(file);
	}
	
}
