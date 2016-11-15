package com.suboch.task1.library.search;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.Album;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
public class AlbumSearcher extends PublicationSearcher<Album> {
    public AlbumSearcher(){}

    public List<Album> search(Library library, Filter filter) {
        Predicate<Album> albumPredicate =(
                p -> (String.valueOf(p.getAlbumCategory())
                        .equalsIgnoreCase(String.valueOf(filter.getAlbumCategory()))));
        super.setPredicate(albumPredicate);
        return (super.search(library,filter));
    }
}
