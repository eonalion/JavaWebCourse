package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.serial.Magazine;
import com.suboch.task1.publication.tag.JournalismTopic;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class MagazineBuilder extends SerialPublicationBuilder<Magazine, MagazineBuilder> {
    private JournalismTopic topic;

    private final int TOPIC = 15;

    public MagazineBuilder(){
        super(new Magazine());
    }

    public MagazineBuilder topic(JournalismTopic topic){
        this.topic = topic;
        return this;
    }

    public Magazine parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        publication = super.parse(data);
        try {
            topic(JournalismTopic.valueOf(dataList.get(TOPIC).toUpperCase()));
        }catch (IndexOutOfBoundsException|IllegalArgumentException e){
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public Magazine build() {
        return new Magazine(publication);
    }
}
