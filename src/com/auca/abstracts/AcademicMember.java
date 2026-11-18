package com.auca.abstracts;

import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;

public abstract class AcademicMember extends Person {

    private String memberNumber;
    private Department department;
    private LocalDate joinDate;
    private String academicStatus;
    private String institutionalEmail;
    private String advisorName;

    public AcademicMember(String personId, String fistName, String lastName, String email,
                          String phoneNumber, LocalDate dateOfBirth, String memberNumber, Department department,
                          LocalDate joinDate, String academicStatus, String institutionalEmail, String advisorName) {
        super(personId, fistName, lastName, email, phoneNumber, dateOfBirth);
        this.memberNumber = memberNumber;
        this.department = department;
        this.joinDate = joinDate;
        this.academicStatus = academicStatus;
        this.institutionalEmail = institutionalEmail;
        this.advisorName = advisorName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    @Override
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(academicStatus);
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (memberNumber == null || memberNumber.trim().length()<4 || memberNumber.trim().length()>30){
            throw new ValidationException("Invalid member number.");
        }
        if (department == null){
            throw new ValidationException("Department is required.");
        }
        if (joinDate == null || joinDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid join date");
        }
        if (!"ACTIVE".equalsIgnoreCase(academicStatus)&& !"ACTIVE".equalsIgnoreCase(academicStatus)){
            throw new ValidationException("Status must be ACTIVE all INACTIVE");
        }
        if (institutionalEmail == null || !institutionalEmail.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || institutionalEmail.length()>100){
            throw new ValidationException("Invalid institution email.");
        }
        if (advisorName == null || advisorName.trim().length()<2 || advisorName.trim().length()>120){
            throw new ValidationException("Invalid advisor name.");
        }
        
    }
    public abstract String getAcademicCategory();

    @Override
    public String toString() {
        return super.toString() +
                "\nMember Number:" + memberNumber +
                "\nDepartment:" + department.getDepartmentName() +
                "\nJoin Date:" + joinDate +
                "\nAcademic status:" + academicStatus +
                "\nInstitution Email:" + institutionalEmail +
                "\nAdvisor Name:" + advisorName;
    }
}
