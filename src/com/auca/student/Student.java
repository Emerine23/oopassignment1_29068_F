package com.auca.student;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;

public class Student extends AcademicMember{
    private String StudentNumber;
    private String programName;
    private LocalDate admissionDate;
    private int studyYear;
    private String guardianPhone;
    private boolean scholarshipStatus;

    public Student(String personId, String fistName, String lastName, String email, String phoneNumber,
                   LocalDate dateOfBirth, String memberNumber, Department department, LocalDate joinDate,
                   String academicStatus, String institutionalEmail, String advisorName, String studentNumber,
                   String programName, LocalDate admissionDate, int stusyYear, String guardianPhone, boolean scholarshipStatus) {
        super(personId, fistName, lastName, email, phoneNumber, dateOfBirth, memberNumber, department, joinDate, academicStatus, institutionalEmail, advisorName);
        this.StudentNumber = studentNumber;
        this.programName = programName;
        this.admissionDate = admissionDate;
        this.studyYear = stusyYear;
        this.guardianPhone = guardianPhone;
        this.scholarshipStatus = scholarshipStatus;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public boolean isScholarshipStatus() {
        return scholarshipStatus;
    }

    public void setScholarshipStatus(boolean scholarshipStatus) {
        this.scholarshipStatus = scholarshipStatus;
    }

    @Override
    public String getRoleDescription() {
        return "Student";
    }

    @Override
    public String getAcademicCategory() {
        return "Student";
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (StudentNumber == null || StudentNumber.trim().length()<4 || StudentNumber.trim().length() > 30){
            throw new ValidationException("Invalid student number.");
        }
        if (programName ==  null || programName.trim().length()<2 || programName.trim().length()>120){
            throw new ValidationException("Invalid program name.");
        }
        if (admissionDate == null || admissionDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid admission date");
        }
        if (studyYear<1 || studyYear>6){
            throw new ValidationException("Study year must be between 1 and 6.");
        }
        if (guardianPhone == null || !guardianPhone.matches("\\+?[0-9]{10,15}")){
            throw new ValidationException("Invalid guardian phone number.");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nStudent Number:" + StudentNumber +
                "\n Program Name:" + programName +
                "\nAdmission Date: " + admissionDate +
                "\n Study year: " + studyYear +
                "\n Guardian phone: " + guardianPhone +
                "\n Scholarship: "+ (scholarshipStatus ? "Yes" : "No");
    }
}
