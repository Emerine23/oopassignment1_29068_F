package com.auca.bookloan;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.student.Student;
import com.auca.librarybook.LibraryBook;

import java.time.LocalDate;
import java.util.Locale;

public class BookLoan implements Identifiable, Registrable{

    private String loanId;
    private Student student;
    private LibraryBook book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    public BookLoan(String loanId, Student student, LibraryBook book, LocalDate borrowedDate, LocalDate dueDate, LocalDate returnedDate) {
        this.loanId = loanId;
        this.student = student;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LibraryBook getBook() {
        return book;
    }

    public void setBook(LibraryBook book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String getId() {
        return loanId;
    }

    @Override
    public String getDisplayName() {
        return student.getDisplayName() + "-" + book.getTitle();
    }

    @Override
    public boolean isActive() {
        return returnedDate == null;
    }

    @Override
    public void validate() throws ValidationException {

        if (loanId == null || loanId.trim().isEmpty()){
            throw new ValidationException("Loan ID is reuired");
        }
        if (student == null){
            throw new ValidationException("A student must be selected");
        }
        if (book == null){
            throw new ValidationException("A book must be selected");
        }
        if (borrowedDate == null || borrowedDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid borrowed date");
        }
        if (dueDate == null || !dueDate.isAfter(borrowedDate)){
            throw new ValidationException("Due date must be after borrowed date");
        }
        if (returnedDate != null && (returnedDate.isBefore(borrowedDate) || returnedDate.isAfter(LocalDate.now()))){
            throw new ValidationException("Invalid returned date");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Book Loan: " + getDisplayName();
    }

    @Override
    public String toString() {
        return  "\nloanId:" + loanId +
                "\nstudent:" + student +
                "\nbook:" + book +
                "\nborrowedDate:" + borrowedDate +
                "\ndueDate:" + dueDate +
                "\nreturnedDate=" + (returnedDate == null ? "Not returned" : returnedDate);
    }
}
