package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.Book;
import com.suboch.task1.publication.Publication;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
public class PublicationSearcher<P extends Publication> implements IPublicationSearch<P> {
    Predicate<P> predicate;

    public PublicationSearcher(){
        predicate = p-> true;
    }

    public void setPredicate (Predicate<P> predicate) {
        this.predicate = predicate;
    }

    public List<P> search(Library library, Filter filter) {
        List<P> publications = (List<P>)library.getPublications();
        Predicate<P> predicate1 = (
                p -> ((!filter.getAuthor().isEmpty() && p.getAuthors().contains(filter.getAuthor())) || filter.getAuthor().isEmpty())
                && ((!filter.getTitle().isEmpty() && p.getTitle().equalsIgnoreCase(filter.getTitle())) || filter.getTitle().isEmpty())
                && ((!filter.getPublisher().isEmpty() && p.getPublisher().equalsIgnoreCase(filter.getPublisher())) || filter.getPublisher().isEmpty())
                && (((!filter.getLanguage().isEmpty() && p.getLanguage().equalsIgnoreCase(filter.getLanguage())) || filter.getLanguage().isEmpty()))
        );
        return publications
                .stream()
                .filter(predicate1.and(predicate))
                .collect(Collectors.toList());
    }
}
