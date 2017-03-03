    package org.kolokolov.simpleproject.model;

import java.math.BigDecimal;

public class EmployeeStatistic {
    
    private String firstName;
    private String lastName;
    private String department;
    
    private BigDecimal salary;
    private BigDecimal avgSalary;
    private BigDecimal avgSalaryInDep;
    private BigDecimal avgSalaryByGender;
    private BigDecimal avgSalaryInDepByGender;
    
    private int sameGenderInDep;
    private int menInDep;
    private int womenInDep;
    private int menTotal;
    private int womenTotal;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public BigDecimal getAvgSalary() {
        return avgSalary;
    }
    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }
    public BigDecimal getAvgSalaryInDep() {
        return avgSalaryInDep;
    }
    public void setAvgSalaryInDep(BigDecimal avgSalaryInDep) {
        this.avgSalaryInDep = avgSalaryInDep;
    }
    public BigDecimal getAvgSalaryByGender() {
        return avgSalaryByGender;
    }
    public void setAvgSalaryByGender(BigDecimal avgSalaryByGender) {
        this.avgSalaryByGender = avgSalaryByGender;
    }
    public BigDecimal getAvgSalaryInDepByGender() {
        return avgSalaryInDepByGender;
    }
    public void setAvgSalaryInDepByGender(BigDecimal avgSalaryInDepByGender) {
        this.avgSalaryInDepByGender = avgSalaryInDepByGender;
    }
    public int getSameGenderInDep() {
        return sameGenderInDep;
    }
    public void setSameGenderInDep(int sameGenderInDep) {
        this.sameGenderInDep = sameGenderInDep;
    }
    public int getMenInDep() {
        return menInDep;
    }
    public void setMenInDep(int menInDep) {
        this.menInDep = menInDep;
    }
    public int getWomenInDep() {
        return womenInDep;
    }
    public void setWomenInDep(int womenInDep) {
        this.womenInDep = womenInDep;
    }
    public int getMenTotal() {
        return menTotal;
    }
    public void setMenTotal(int menTotal) {
        this.menTotal = menTotal;
    }
    public int getWomenTotal() {
        return womenTotal;
    }
    public void setWomenTotal(int womenTotal) {
        this.womenTotal = womenTotal;
    }
}
