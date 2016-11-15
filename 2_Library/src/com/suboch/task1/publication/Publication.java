package com.suboch.task1.publication;

import com.suboch.task1.publication.tag.PublicationFormat;
import com.suboch.task1.publication.tag.IllustrationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */

public class Publication {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publicationCity;
    private String publicationCountry;
    private LocalDate publicationDate;
    private String language;
    private IllustrationType illustration;

    private int pageAmount;
    private String dimensions;
    private int weight;
    private PublicationFormat format;

    private int ID;

    public Publication(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustration, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID) {
        this.setTitle(title);
        this.setAuthors(authors);
        this.setPublisher(publisher);
        this.setPublicationCity(publicationCity);
        this.setPublicationCountry(publicationCountry);
        this.setPublicationDate(publicationDate);
        this.language = language;
        this.illustration = illustration;
        this.pageAmount = pageAmount;
        this.dimensions = dimensions;
        this.weight = weight;
        this.format = format;
        //Generate it!
        this.ID = ID;
    }

    public Publication() {
        this.title = "";
        this.authors = new ArrayList<>();
        this.publisher = "";
        this.publicationCity = "";
        this.publicationCountry = "";
        this.publicationDate = LocalDate.parse("0000-01-01");
        this.language = "";
        this.illustration = IllustrationType.NONE;
        this.dimensions = "";
        this.format = PublicationFormat.NONE;
    }

    public Publication(Publication publication) {
        this.title = publication.getTitle();
        this.authors = publication.getAuthors();
        this.publisher = publication.getPublisher();
        this.publicationCity = publication.getPublicationCity();
        this.publicationCountry = publication.getPublicationCountry();
        this.publicationDate = publication.getPublicationDate();
        this.language = publication.getLanguage();
        this.illustration = publication.getIllustration();
        this.pageAmount = publication.getPageAmount();
        this.dimensions = publication.getDimensions();
        this.weight = publication.getWeight();
        this.format = publication.getFormat();
        this.ID = publication.getID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationCity() {
        return publicationCity;
    }

    public void setPublicationCity(String publicationCity) {
        this.publicationCity = publicationCity;
    }

    public String getPublicationCountry() {
        return publicationCountry;
    }

    public void setPublicationCountry(String publicationCountry) {
        this.publicationCountry = publicationCountry;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public IllustrationType getIllustration() {
        return illustration;
    }

    public void setIllustration(IllustrationType illustration) {
        this.illustration = illustration;
    }

    public int getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(int pageAmount) {
        this.pageAmount = pageAmount;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PublicationFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationFormat format) {
        this.format = format;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publication that = (Publication) o;

        if (pageAmount != that.pageAmount) return false;
        if (weight != that.weight) return false;
        if (ID != that.ID) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (authors != null ? !authors.equals(that.authors) : that.authors != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (publicationCity != null ? !publicationCity.equals(that.publicationCity) : that.publicationCity != null)
            return false;
        if (publicationCountry != null ? !publicationCountry.equals(that.publicationCountry) : that.publicationCountry != null)
            return false;
        if (publicationDate != null ? !publicationDate.equals(that.publicationDate) : that.publicationDate != null)
            return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (illustration != that.illustration) return false;
        if (dimensions != null ? !dimensions.equals(that.dimensions) : that.dimensions != null) return false;
        return format == that.format;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (publicationCity != null ? publicationCity.hashCode() : 0);
        result = 31 * result + (publicationCountry != null ? publicationCountry.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (illustration != null ? illustration.hashCode() : 0);
        result = 31 * result + pageAmount;
        result = 31 * result + (dimensions != null ? dimensions.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + ID;
        return result;
    }

    @Override
    public String toString() {
        return  "title=" + title   +
                ", authors=" + authors  +
                ", publisher=" + publisher +
                ", publicationCity=" + publicationCity +
                ", publicationCountry=" + publicationCountry +
                ", publicationDate=" + publicationDate +
                ", language=" + language +
                ", illustration=" + String.valueOf(illustration).toLowerCase() +
                ", pageAmount=" + pageAmount +
                ", dimensions=" + dimensions +
                ", weight=" + weight +
                ", format=" + String.valueOf(format).toLowerCase() +
                ", ID=" + ID;
    }
}
