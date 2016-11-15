package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.serial.Newspaper;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
public class NewspaperSearcher extends SerialPublicationSearcher<Newspaper> {
    public NewspaperSearcher(){}

    public List<Newspaper> search(Library library, Filter filter) {
        Predicate<Newspaper> newspaperPredicate = (
                p -> (String.valueOf(p.getTopic()).equals(String.valueOf(filter.getJournalismTopic()))
                        && (String.valueOf(p.getGeographicalScope()).equals(String.valueOf(filter.getGeographicalScope())))));
        super.setPredicate(newspaperPredicate);
        return (super.search(library,filter));
    }
}
