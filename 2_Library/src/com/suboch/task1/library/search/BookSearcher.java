package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.Book;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
public class BookSearcher extends PublicationSearcher<Book> {
    public BookSearcher(){}

    public List<Book> search(Library library, Filter filter) {
        Predicate<Book> bookPredicate =(
                p -> (!filter.getIsbn().isEmpty() && p.getIsbn().contains(filter.getIsbn()))
                        && (p.getBookCategory().containsAll(filter.getBookCategory())));
        super.setPredicate(bookPredicate);
        return (super.search(library,filter));
    }
}
