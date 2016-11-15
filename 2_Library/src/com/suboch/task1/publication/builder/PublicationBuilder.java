package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.Publication;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.PublicationFormat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public abstract class PublicationBuilder<P extends Publication, B extends PublicationBuilder<P,B>> implements IPublicationBuilder<P> {
    protected P publication;

    private final int TITLE = 1;
    private final int AUTHORS = 2;
    private final int PUBLISHER = 3;
    private final int PUBLICATION_CITY = 4;
    private final int PUBLICATION_COUNTRY = 5;
    private final int PUBLICATION_DATE = 6;
    private final int LANGUAGE = 7;
    private final int ILLUSTRATION = 8;
    private final int PAGE_AMOUNT = 9;
    private final int DIMENSIONS = 10;
    private final int WEIGHT = 11;
    private final int PUBLICATION_FORMAT = 12;

    public PublicationBuilder(P publication){
        this.publication = publication;
    }

    public B title(String title) {
        publication.setTitle(title);
        return (B)this;
    }

    public B authors(List<String> authors) {
        publication.setAuthors(authors);
        return (B)this;
    }

    public B publisher(String publisher) {
        publication.setPublisher(publisher);
        return (B)this;
    }

    public B publicationCity(String city) {
        publication.setPublicationCity(city);
        return (B)this;
    }

    public B publicationCountry(String country) {
        publication.setPublicationCountry(country);
        return (B)this;
    }

    public B publicationDate(LocalDate date) {
        publication.setPublicationDate(date);
        return (B)this;
    }

    public B language(String language) {
        publication.setLanguage(language);
        return (B)this;
    }

    public B illustration(IllustrationType illustration) {
        publication.setIllustration(illustration);
        return (B)this;
    }

    public B pageAmount(int pageAmount) {
        publication.setPageAmount(pageAmount);
        return (B)this;
    }

    public B dimensions(String dimensions) {
        publication.setDimensions(dimensions);
        return (B)this;
    }

    public B weight(int weight) {
        publication.setWeight(weight);
        return (B)this;
    }

    public B publicationFormat(PublicationFormat format) {
        publication.setFormat(format);
        return (B)this;
    }

    public P parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        try {
            title(dataList.get(TITLE))
                    .authors(Arrays.asList(dataList.get(AUTHORS).split(",")))
                    .publisher(dataList.get(PUBLISHER))
                    .publicationCity(dataList.get(PUBLICATION_CITY))
                    .publicationCountry(dataList.get(PUBLICATION_COUNTRY))
                    .publicationDate(LocalDate.parse(dataList.get(PUBLICATION_DATE)))
                    .language(dataList.get(LANGUAGE))
                    .illustration(IllustrationType.valueOf(dataList.get(ILLUSTRATION).toUpperCase()))
                    .pageAmount(Integer.valueOf(dataList.get(PAGE_AMOUNT).toUpperCase()))
                    .dimensions(dataList.get(DIMENSIONS))
                    .weight(Integer.parseInt(dataList.get(WEIGHT)))
                    .publicationFormat(PublicationFormat.valueOf(dataList.get(PUBLICATION_FORMAT).toUpperCase()));
        } catch (IndexOutOfBoundsException|DateTimeException|IllegalArgumentException e) {
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public P build(){
        return publication;
    }
}
