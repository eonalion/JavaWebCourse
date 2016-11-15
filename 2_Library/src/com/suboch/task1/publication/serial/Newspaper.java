package com.suboch.task1.publication.serial;

import com.suboch.task1.publication.tag.GeographicalScope;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.JournalismTopic;
import com.suboch.task1.publication.tag.PublicationFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 */
public class Newspaper extends SerialPublication {
    private JournalismTopic topic;
    private GeographicalScope geographicalScope;

    public Newspaper(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustrations, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID, Period periodicity, int issue, JournalismTopic topic, GeographicalScope geographicalScope) {
        super(title, authors, publisher, publicationCity, publicationCountry, publicationDate, language, illustrations, pageAmount, dimensions, weight, format, ID, periodicity, issue);
        this.topic = topic;
        this.geographicalScope = geographicalScope;
    }

    public Newspaper(Newspaper newspaper){
        super(newspaper);
        this.topic = newspaper.getTopic();
        this.geographicalScope = newspaper.getGeographicalScope();
    }

    public Newspaper(){
        this.topic = JournalismTopic.NONE;
        this.geographicalScope = GeographicalScope.NONE;
    }

    public JournalismTopic getTopic() {
        return topic;
    }

    public void setTopic(JournalismTopic topic) {
        this.topic = topic;
    }

    public GeographicalScope getGeographicalScope() {
        return geographicalScope;
    }

    public void setGeographicalScope(GeographicalScope geographicalScope) {
        this.geographicalScope = geographicalScope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Newspaper newspaper = (Newspaper) o;

        if (topic != newspaper.topic) return false;
        return geographicalScope == newspaper.geographicalScope;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (geographicalScope != null ? geographicalScope.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                super.toString()+
                ", topic=" + String.valueOf(topic).toLowerCase() +
                ", geographicalScope=" + String.valueOf(geographicalScope).toLowerCase() +
                '}';
    }
}
