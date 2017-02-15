package org.kolokolov.simpleproject.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Part;

@Controller
@ManagedBean
@RequestScope
public class FileDownloader {
	
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	
	private Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

	private String fileId = params.get("fileId");
	
	@Autowired
	private FileService fileService;
	
	private Part uploadedFile;
	
	public void download() {
		EmployeeFile file = fileService.getFile(fileId);
		byte[] data = file.getData();
		String fileName = file.getName();
        ec.responseReset();
        ec.setResponseContentType("text/plain");
        ec.setResponseContentLength(data.length);
        String attachmentName = "attachment; filename=\"" + fileName + "\"";
        ec.setResponseHeader("Content-Disposition", attachmentName);
        try {
            OutputStream output = ec.getResponseOutputStream();
            IOUtils.copy(new ByteArrayInputStream(data), output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        fc.responseComplete();
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
}
