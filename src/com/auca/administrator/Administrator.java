package com.auca.administrator;

import com.auca.abstracts.Person;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;
import java.util.Locale;

public class Administrator extends Person{

    private String staffNumber;
    private String jobTitle;
    private String assignedOffice;
    private LocalDate employmentDate;
    private int accessLevel;
    private boolean active;

    public Administrator(String personId, String fistName, String lastName,
                         String email, String phoneNumber, LocalDate dateOfBirth,
                         String staffNumber, String jobTitle, String assignedOffice,
                         LocalDate employmentDate, int accessLevel, boolean active) {
        super(personId, fistName, lastName, email, phoneNumber, dateOfBirth);
        this.staffNumber = staffNumber;
        this.jobTitle = jobTitle;
        this.assignedOffice = assignedOffice;
        this.employmentDate = employmentDate;
        this.accessLevel = accessLevel;
        this.active = active;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }

    public void setAssignedOffice(String assignedOffice) {
        this.assignedOffice = assignedOffice;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getRoleDescription() {
        return "Administrator";
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (staffNumber == null || staffNumber.trim().isEmpty()){
            throw new ValidationException("Staff number is required");
        }
        if (jobTitle == null || staffNumber.trim().isEmpty()){
            throw new ValidationException("Assigned office is required");
        }
        if (assignedOffice == null || assignedOffice.trim().isEmpty()){
            throw new ValidationException("Assigned office is required");
        }
        if (employmentDate ==null || employmentDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid employment date");
        }
        if (accessLevel < 1){
            throw new ValidationException("Access level must be at least 1");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nstaffNumber:" + staffNumber +
                "\njobTitle:" + jobTitle +
                "\nassignedOffice:" + assignedOffice +
                "\nemploymentDate:" + employmentDate +
                "\naccessLevel:" + accessLevel +
                "\nactive:" + active;
    }
}
