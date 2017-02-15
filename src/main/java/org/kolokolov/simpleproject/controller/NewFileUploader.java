package org.kolokolov.simpleproject.controller;

import java.io.InputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.kolokolov.simpleproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Part;

@Controller
@ManagedBean
@RequestScope
public class NewFileUploader {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileService fileService;
	
	private Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	private String employeeId = params.get("employeeId");
	
	private Part uploadedFile;
	
	public void save() {
		logger.debug("Params = " + params);
		logger.debug("EmployeeId = " + employeeId);
		logger.debug("Save method runs");
		String fileName = uploadedFile.getSubmittedFileName();
		try (InputStream inputStream = uploadedFile.getInputStream()) {
			byte[] data = IOUtils.toByteArray(inputStream);
			logger.debug("Getting employee for ID: " + employeeId);
			Employee employee = employeeService.getEmployeeById(employeeId);
			logger.debug("Saveing file to employee: " + employee);
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

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
