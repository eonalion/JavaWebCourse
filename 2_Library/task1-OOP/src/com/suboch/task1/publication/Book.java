package com.suboch.task1.publication;

import com.suboch.task1.publication.tag.BookCategory;
import com.suboch.task1.publication.tag.PublicationFormat;
import com.suboch.task1.publication.tag.IllustrationType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Book extends Publication {
    private String isbn;
    private List<BookCategory> bookCategory;

    public Book(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustrations, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID, String isbn, List<BookCategory> category, PublicationFormat format1) {
        super(title, authors, publisher, publicationCity, publicationCountry, publicationDate, language, illustrations, pageAmount, dimensions, weight, format, ID);
        this.isbn = isbn;
        this.bookCategory = category;
    }

    public Book(Book book){
        super(book);
        this.isbn = book.getIsbn();
        this.bookCategory = book.getBookCategory();
    }

    public Book() {
        this.isbn="";
        bookCategory = Arrays.asList(BookCategory.NONE);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookCategory> getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        return bookCategory != null ? bookCategory.equals(book.bookCategory) : book.bookCategory == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookCategory != null ? bookCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                ", isbn=" + isbn +
                ", bookCategory=" + bookCategory.toString().toLowerCase() +
                '}';
    }
}
