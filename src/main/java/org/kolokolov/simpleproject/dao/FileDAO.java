package org.kolokolov.simpleproject.dao;

import java.util.List;
import java.util.Map;

import org.kolokolov.simpleproject.model.EmployeeFile;

public interface FileDAO {
	
	List<EmployeeFile> getEmployeeFiles(int employeeId);
	
	EmployeeFile getFile(int fileId);
	
	void saveFile(EmployeeFile file);
	
	Map<Integer,String> getFileDescriptions(int employeeId);
}
