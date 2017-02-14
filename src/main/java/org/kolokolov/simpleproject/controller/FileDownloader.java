package org.kolokolov.simpleproject.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
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
public class FileDownloader {
	
	private static Logger logger = LogManager.getLogger();
	
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	
	private Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

	private String employeeId = params.get("employeeId");
	
	@Autowired
	private EmployeeService employeeService;
	
	private Part uploadedFile;
	
	public void download() {
		byte[] exportContent = employeeService.getResume(employeeId);
		if (exportContent != null) {
	        ec.responseReset();
	        ec.setResponseContentType("text/plain");
	        ec.setResponseContentLength(exportContent.length);
	        String attachmentName = "attachment; filename=\"export.txt\"";
	        ec.setResponseHeader("Content-Disposition", attachmentName);
	        try {
	            OutputStream output = ec.getResponseOutputStream();
	            IOUtils.copy(new ByteArrayInputStream(exportContent), output);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	
	        fc.responseComplete();
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
