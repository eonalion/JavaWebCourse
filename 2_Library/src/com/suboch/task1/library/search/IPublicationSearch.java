package com.suboch.task1.library.search;

/**
 *
 */

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.Publication;

import java.util.List;


public interface IPublicationSearch<P extends Publication> {
        List<P> search(Library library, Filter filter);
}
