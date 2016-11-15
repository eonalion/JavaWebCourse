package com.suboch.task1.factory;

import com.suboch.task1.publication.builder.*;
import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.publication.PublicationEnum;

/**
 *
 */
public class PublicationBuilderFactory {
    public PublicationBuilder newPublicationBuilder(String publicationType) throws IllegalPublicationTypeException {
        PublicationEnum publicationName;

        try {
            publicationName = PublicationEnum.valueOf(publicationType.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new IllegalPublicationTypeException(e);
        }

        switch (publicationName){
            case ALBUM:
                return new AlbumBuilder();
            case BOOK:
                return new BookBuilder();
            case MAGAZINE:
                return new MagazineBuilder();
            case NEWSPAPER:
                return new NewspaperBuilder();
            default:
                throw new IllegalPublicationTypeException();
        }
    }
}
