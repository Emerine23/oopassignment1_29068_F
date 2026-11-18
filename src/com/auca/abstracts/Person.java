package com.auca.abstracts;

import com.auca.interfaces.Identifiable;
import  com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;

public abstract class Person implements Identifiable, Registrable {

    private String personId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Person(String personId, String fistName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.personId = personId;
        this.firstName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFistName() {
        return firstName;
    }

    public void setFistName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getId() {
        return personId;
    }

    @Override
    public String getDisplayName() {
        return firstName + " " + lastName;
    }


    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return getRoleDescription() + "" + getDisplayName() + "(ID:" + personId +")";
    }

    public abstract String getRoleDescription();


    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public void validate() throws ValidationException {
        if(personId == null || personId.trim().length()<4 || personId.trim().length()>30){
            throw new ValidationException("Person id must have 4-30 characters.");
        }
        if (firstName == null || firstName.trim().length()<2 || firstName.trim().length()>60){
            throw new ValidationException("First name must have 2-60 characters.");
        }
        if(lastName == null || lastName.trim().length()<2 || lastName.trim().length()>60){
            throw new ValidationException("Last name must have 2-60 characters");
        }
        if (email== null || email.length()>100
                || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            throw new ValidationException("Invalid email address.");
        }
        if (phoneNumber == null || !phoneNumber.matches("\\+?[0-9]{10,15}")){
            throw new ValidationException("Phone number must contain 10-15 didits");
        }
        if (dateOfBirth == null || !dateOfBirth.isBefore(LocalDate.now())){
            throw new ValidationException("Date of birth must be in the past");
        }


    }
}
