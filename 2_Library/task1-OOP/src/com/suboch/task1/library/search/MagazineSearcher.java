package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.serial.Magazine;
import com.suboch.task1.publication.serial.Newspaper;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
public class MagazineSearcher  extends SerialPublicationSearcher<Magazine> {
    public MagazineSearcher(){}

    public List<Magazine> search(Library library, Filter filter) {
        Predicate<Magazine> magazinePredicate = (
                p -> (String.valueOf(p.getTopic()).equals(String.valueOf(filter.getJournalismTopic()))));
        super.setPredicate(magazinePredicate);
        return (super.search(library, filter));
    }
}
