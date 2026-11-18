package com.auca.librarybook;

import com.auca.interfaces.Registrable;
import com.auca.interfaces.Identifiable;
import com.auca.exceptions.ValidationException;

import java.util.Locale;

public class LibraryBook implements Identifiable, Registrable{

    private String bookId;
    private String isbn;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public LibraryBook(String bookId, String isbn, String title, String author, int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String getId() {
        return bookId;
    }

    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public boolean isActive() {
        return availableCopies>0;
    }

    @Override
    public void validate() throws ValidationException {

        if (bookId == null || bookId.trim().isEmpty()){
            throw new ValidationException("Book ID is required");
        }
        if (isbn == null || isbn.trim().isEmpty()){
            throw new ValidationException("ISBN is required");
        }
        if (title == null || title.trim().isEmpty()){
            throw new ValidationException("Book name is required");
        }
        if (author == null || author.trim().isEmpty()){
            throw new ValidationException("Author name is required");
        }
        if (totalCopies<0){
            throw new ValidationException("Total copies cannot be negative");
        }
        if (availableCopies<0){
            throw new ValidationException("Available copies cannot be negative");
        }
        if (availableCopies>totalCopies){
            throw new ValidationException("Available copies cannot exceed total copies");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Book:" + title + "(Available Copies:" + ")";
    }

    @Override
    public String toString() {
        return   "\nbookId:" + bookId +
                "\nisbn:" + isbn+
                "\ntitle:" + title+
                "\nauthor:" + author +
                "\ntotalCopies:" + totalCopies +
                "\navailableCopies:" + availableCopies;
    }
}
