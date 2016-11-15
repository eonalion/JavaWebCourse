package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.Book;
import com.suboch.task1.publication.tag.BookCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class BookBuilder extends PublicationBuilder<Book, BookBuilder> {
    private final int ISBN = 13;
    private final int BOOK_CATEGORY = 14;

    public BookBuilder() {
        super(new Book());
    }

    public BookBuilder isbn(String isbn) {
        publication.setIsbn(isbn);
        return this;
    }

    public BookBuilder bookCategory(List<BookCategory> bookCategory) {
        publication.setBookCategory(bookCategory);
        return this;
    }

    public Book parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        publication = super.parse(data);
        try {
            publication.setIsbn(dataList.get(ISBN));
            publication.setBookCategory(Arrays.stream(dataList.get(BOOK_CATEGORY).split(",")).map(name->BookCategory.valueOf(name.toUpperCase())).collect(Collectors.toList()));
        } catch (IndexOutOfBoundsException|IllegalArgumentException e){
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public Book build(){
        return new Book(publication);
    }
}