package com.suboch.task1.publication.serial;

import com.suboch.task1.publication.tag.AlbumCategory;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.JournalismTopic;
import com.suboch.task1.publication.tag.PublicationFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 */
public class Magazine extends SerialPublication {
    private JournalismTopic topic;

    public Magazine(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustrations, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID, Period periodicity, int issue, JournalismTopic topic) {
        super(title, authors, publisher, publicationCity, publicationCountry, publicationDate, language, illustrations, pageAmount, dimensions, weight, format, ID, periodicity, issue);
        this.topic = topic;
    }

    public Magazine(Magazine magazine){
        super(magazine);
        this.topic = magazine.getTopic();
    }

    public Magazine(){
        this.topic = JournalismTopic.NONE;
    }

    public JournalismTopic getTopic() {
        return topic;
    }

    public void setTopic(JournalismTopic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Magazine magazine = (Magazine) o;

        return topic == magazine.topic;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                super.toString()+
                ", topic=" + String.valueOf(topic).toLowerCase() +
                '}';
    }
}
