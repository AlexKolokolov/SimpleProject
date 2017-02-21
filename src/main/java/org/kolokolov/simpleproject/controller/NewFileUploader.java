package org.kolokolov.simpleproject.controller;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Part;

@Controller
@ManagedBean
@RequestScope
public class NewFileUploader {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private FileService fileService;
	
	private Employee employee;
	
	private Part uploadedFile;
	
	@Secured({"ROLE_ADMIN"})
	public void save() {
		String fileName = uploadedFile.getSubmittedFileName();
		try (InputStream inputStream = uploadedFile.getInputStream()) {
			byte[] data = IOUtils.toByteArray(inputStream);
			logger.debug("Saving file to employee: " + employee);
			fileService.saveFile(new EmployeeFile(fileName, data, employee));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
