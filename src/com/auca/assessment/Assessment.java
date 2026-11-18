package com.auca.assessment;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.enrollment.Enrollment;

import java.time.LocalDate;

public class Assessment implements Identifiable, Registrable{

    private String assessmentId;
    private Enrollment enrollment;
    private String assessmentType;
    private double maximumScore;
    private double obtainedScore;
    private LocalDate assessmentDate;

    public Assessment(String assessmentId, Enrollment enrollment, String assessmentType, double maximumScore, double obtainedScore, LocalDate assessmentDate) {
        this.assessmentId = assessmentId;
        this.enrollment = enrollment;
        this.assessmentType = assessmentType;
        this.maximumScore = maximumScore;
        this.obtainedScore = obtainedScore;
        this.assessmentDate = assessmentDate;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public double getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(double maximumScore) {
        this.maximumScore = maximumScore;
    }

    public double getObtainedScore() {
        return obtainedScore;
    }

    public void setObtainedScore(double obtainedScore) {
        this.obtainedScore = obtainedScore;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    @Override
    public String getId() {
        return assessmentId;
    }

    @Override
    public String getDisplayName() {
        return assessmentType +"-"+ enrollment.getStudent().getDisplayName();
    }

    @Override
    public boolean isActive() {
        return enrollment != null && enrollment.isActive();
    }

    @Override
    public void validate() throws ValidationException {

        if (assessmentId == null || assessmentId.trim().isEmpty()){
            throw new ValidationException("Assessment ID is required");
        }
        if (enrollment == null || !enrollment.isActive()){
            throw new ValidationException("Assessment requires an active enrollment");
        }
        if (assessmentType == null || assessmentType.trim().isEmpty()){
            throw new ValidationException("Assessment type is required");
        }
        if (maximumScore<=0){
            throw new ValidationException("Maximum score must be greater than zero");
        }
        if (obtainedScore<0 || obtainedScore>maximumScore){
            throw new ValidationException("obtained scre must between zero and maximum score");
        }
        if (assessmentDate == null || assessmentDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid assessment date");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Assessment:" + assessmentType + "-Score:" + obtainedScore+ "/"+ maximumScore;
    }

    @Override
    public String toString() {
        return "\nassessmentId:" + assessmentId+
                "\nenrollment:" + enrollment +
                "\nassessmentType:" + assessmentType +
                "\nmaximumScore:" + maximumScore +
                "\nobtainedScore:" + obtainedScore +
                "\nassessmentDate=" + assessmentDate;
    }
}
