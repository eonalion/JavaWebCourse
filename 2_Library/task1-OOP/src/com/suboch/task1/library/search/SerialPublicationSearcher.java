package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.serial.SerialPublication;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */

public class SerialPublicationSearcher<P extends SerialPublication> extends PublicationSearcher<P> {
    private Predicate<P> predicate;

    public SerialPublicationSearcher(){}

    public void setPredicate(Predicate<P> predicate) {
        this.predicate = predicate;
    }

    public List<P> search(Library library, Filter filter) {
        Predicate<P> serialPublicationPredicate = (p -> (
                p.getPeriodicity().toString().equalsIgnoreCase(filter.getPeriodicity().toString()))
                && (p.getIssue() > 0 && p.getIssue() == filter.getIssue()));
        super.setPredicate(serialPublicationPredicate.and(this.predicate));
        return (super.search(library, filter));
    }
}
