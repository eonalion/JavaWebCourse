package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.serial.SerialPublication;

import java.time.DateTimeException;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public abstract class SerialPublicationBuilder<P extends SerialPublication, B extends SerialPublicationBuilder<P,B>> extends PublicationBuilder<P, B> {
    private final int PERIODICITY = 13;
    private final int ISSUE = 14;

    public SerialPublicationBuilder(P publication) {
        super(publication);
    }

    public SerialPublicationBuilder periodicity(Period period) {
        publication.setPeriodicity(period);
        return this;
    }

    public SerialPublicationBuilder issue(int issue) {
        publication.setIssue(issue);
        return this;
    }

    public P parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        try {
            publication = super.parse(data);
            periodicity(Period.parse(dataList.get(PERIODICITY).toUpperCase()))
                    .issue(Integer.parseInt(dataList.get(ISSUE)));
        } catch (IndexOutOfBoundsException|DateTimeException|IllegalArgumentException e){
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public P build(){
        return publication;
    }
}
