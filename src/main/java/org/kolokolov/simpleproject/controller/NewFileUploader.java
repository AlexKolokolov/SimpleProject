package org.kolokolov.simpleproject.controller;

import java.io.InputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Part;

@Controller
@ManagedBean
@RequestScope
public class NewFileUploader {
	
	private static Logger logger = LogManager.getLogger();
	
	private Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

	private String employeeId = params.get("employeeId");
	
	@Autowired
	private EmployeeService employeeService;
	
	private Part uploadedFile;
	
	public void save() {
		logger.debug("Save method runs");
		try (InputStream inputStream = uploadedFile.getInputStream()) {
			byte[] bytes = IOUtils.toByteArray(inputStream);
			employeeService.addFileToEmployee(employeeId, bytes);
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
}
