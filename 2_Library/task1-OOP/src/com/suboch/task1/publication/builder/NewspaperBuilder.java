package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.serial.Newspaper;
import com.suboch.task1.publication.tag.GeographicalScope;
import com.suboch.task1.publication.tag.JournalismTopic;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class NewspaperBuilder extends SerialPublicationBuilder<Newspaper, NewspaperBuilder> {
    private final int TOPIC = 15;
    private final int SCOPE = 16;

    public NewspaperBuilder() {
        super(new Newspaper());
    }

    public NewspaperBuilder topic(JournalismTopic topic) {
        publication.setTopic(topic);
        return this;
    }

    public NewspaperBuilder geographicalScope(GeographicalScope geographicalScope) {
        publication.setGeographicalScope(geographicalScope);
        return this;
    }

    public Newspaper parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        publication = super.parse(data);
        try {
            topic(JournalismTopic.valueOf(dataList.get(TOPIC).toUpperCase()))
            .geographicalScope(GeographicalScope.valueOf(dataList.get(SCOPE).toUpperCase()));
        } catch (IndexOutOfBoundsException | DateTimeException | IllegalArgumentException e) {
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public Newspaper build() {
        return new Newspaper(publication);
    }
}
