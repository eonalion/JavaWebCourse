package com.suboch.task1.library.search;

import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.publication.PublicationEnum;

/**
 *
 */
public class PublicationSearcherCreator {
    public PublicationSearcher create(String type) throws IllegalPublicationTypeException {
        switch (PublicationEnum.valueOf(type.toUpperCase())) {
            case BOOK:
                return new BookSearcher();
            case ALBUM:
                return new AlbumSearcher();
            case MAGAZINE:
                return new MagazineSearcher();
            case NEWSPAPER:
                return new NewspaperSearcher();
            case ALL:
                return new PublicationSearcher();
            default:
                throw new IllegalPublicationTypeException();
        }
    }
}
