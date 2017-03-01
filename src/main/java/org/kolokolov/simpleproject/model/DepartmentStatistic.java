package org.kolokolov.simpleproject.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DepartmentStatistic {

    private String department;
    
    private int employeesNumber;
    
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private BigDecimal totalSalary;
    private BigDecimal averageSalary;
    
    private int womenNumber;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getWomenNumber() {
        return womenNumber;
    }

    public void setWomenNumber(int womenNumber) {
        this.womenNumber = womenNumber;
    }

    public BigDecimal getAverageSalary() {
        if (averageSalary != null) {
            averageSalary = averageSalary.setScale(2, RoundingMode.HALF_UP);
        }
        return averageSalary;
    }

    public void setAverageSalary(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }
}
