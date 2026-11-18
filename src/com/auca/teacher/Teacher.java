package com.auca.teacher;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Teacher extends AcademicMember{

    private String employeeNumber;
    private String specialization;
    private LocalDate hireDate;
    private BigDecimal salary;
    private String officeNumber;
    private String employmentType;

    public Teacher(String personId, String fistName, String lastName, String email,
                   String phoneNumber, LocalDate dateOfBirth, String memberNumber,
                   Department department, LocalDate joinDate, String academicStatus,
                   String institutionalEmail, String advisorName, String employeeNumber,
                   String specialization, LocalDate hireDate, BigDecimal salary, String officeNumber, String employmentType) {
        super(personId, fistName, lastName, email, phoneNumber, dateOfBirth, memberNumber, department, joinDate, academicStatus, institutionalEmail, advisorName);
        this.employeeNumber = employeeNumber;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.salary = salary;
        this.officeNumber = officeNumber;
        this.employmentType = employmentType;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String getRoleDescription() {
        return "Teacher";
    }

    @Override
    public String getAcademicCategory() {
        return "Teaching staff";
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (employeeNumber == null || employeeNumber.trim().length()<4 || employeeNumber.trim().length()>30){
            throw new ValidationException("Invalid employee number");
        }
        if (specialization == null || specialization.trim().isEmpty()){
            throw new ValidationException("Specialization is required");
        }
        if (hireDate == null || hireDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid hire date");
        }
        if (salary == null || salary.compareTo(BigDecimal.ZERO)<=0){
            throw new ValidationException("salary must be greater than zero");
        }
        if (officeNumber == null || officeNumber.trim().isEmpty()){
            throw new ValidationException("office number is required");
        }
        if (employmentType == null || (!employmentType.equalsIgnoreCase("full-time")&&!employmentType.equalsIgnoreCase("part-time")&&!employmentType.equalsIgnoreCase("contract"))){
            throw new ValidationException("Employment type must be full-time, part-time or contract");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n employeeNumber:" + employeeNumber +
                "\nspecialization:" + specialization +
                "\nhireDate:" + hireDate +
                "\nsalary:" + salary +
                "\nofficeNumber:" + officeNumber +
                "\nemploymentType='" + employmentType;
    }
}
