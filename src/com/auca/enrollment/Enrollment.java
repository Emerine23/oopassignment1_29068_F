package com.auca.enrollment;

import com.auca.course.Course;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.student.Student;
import com.auca.teacher.Teacher;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;

public class Enrollment implements Identifiable, Registrable{

    private String enrollmentId;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private String semester;
    private String enrollmentStatus;

    public Enrollment(Student student, String enrollmentId, Course course, LocalDate enrollmentDate, String semester, String enrollmentStatus) {
        this.student = student;
        this.enrollmentId = enrollmentId;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.semester = semester;
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    @Override
    public String getId() {
        return enrollmentId;
    }

    @Override
    public String getDisplayName() {
        return student.getDisplayName()+ "-" + course.getCourseTitle();
    }

    @Override
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(enrollmentStatus);
    }

    @Override
    public void validate() throws ValidationException {

        if (enrollmentId == null || enrollmentId.trim().isEmpty()){
            throw new ValidationException("Enrollment ID is require");
        }
        if (student == null){
            throw new ValidationException("A student must be selected");
        }
        if (course == null){
            throw new ValidationException("A course must be slected");
        }
        if (enrollmentDate == null || enrollmentDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid enrollment date.");
        }
        if (semester == null || semester.trim().isEmpty()){
            throw new ValidationException("Semester is required.");
        }
        if (enrollmentStatus == null|| enrollmentStatus.trim().isEmpty()){
            throw new ValidationException("Enrollment status is required.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Enrollment:" + getDisplayName();
    }

    @Override
    public String toString() {
        return  "\nenrollmentId:" + enrollmentId+
                "\nstudent:" + student +
                "\ncourse:" + course +
                "\nenrollmentDate:" + enrollmentDate +
                "\nsemester='" + semester+
                "\nenrollmentStatus='" + enrollmentStatus;
    }
}
