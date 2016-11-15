package com.suboch.task1.publication.serial;

import com.suboch.task1.publication.Publication;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.PublicationFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 */
public abstract class SerialPublication extends Publication {
    private Period periodicity;
    private int issue;

    public SerialPublication(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustrations, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID, Period periodicity, int issue) {
        super(title, authors, publisher, publicationCity, publicationCountry, publicationDate, language, illustrations, pageAmount, dimensions, weight, format, ID);
        this.periodicity = periodicity;
        this.issue = issue;
    }

    public SerialPublication(SerialPublication serialPublication) {
        super(serialPublication);
        this.periodicity = serialPublication.getPeriodicity();
        this.issue = serialPublication.getIssue();
    }

    public SerialPublication() {
        this.periodicity = Period.parse("p0y0m0d");
    }

    public Period getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Period periodicity) {
        this.periodicity = periodicity;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SerialPublication that = (SerialPublication) o;

        if (issue != that.issue) return false;
        return periodicity != null ? periodicity.equals(that.periodicity) : that.periodicity == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (periodicity != null ? periodicity.hashCode() : 0);
        result = 31 * result + issue;
        return result;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", periodicity=" + periodicity.toString().substring(1) +
                ", issue=" + issue;
    }
}
