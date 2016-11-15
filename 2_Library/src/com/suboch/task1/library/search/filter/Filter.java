package com.suboch.task1.library.search.filter;

import com.suboch.task1.publication.Book;
import com.suboch.task1.publication.tag.AlbumCategory;
import com.suboch.task1.publication.tag.BookCategory;
import com.suboch.task1.publication.tag.GeographicalScope;
import com.suboch.task1.publication.tag.JournalismTopic;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public  class Filter {
    private String publicationType;
    private String author;
    private String title;
    private String publisher;
    private String language;
    private String isbn;
    private List<BookCategory> bookCategory;
    private AlbumCategory albumCategory;
    private Period periodicity;
    private int issue;
    private JournalismTopic journalismTopic;
    private GeographicalScope geographicalScope;

    public Filter() {
        this.publicationType = "";
        this.author = "";
        this.title = "";
        this.publisher = "";
        this.language = "";
        this.isbn = "";
        this.bookCategory = new ArrayList<>();
        this.albumCategory = AlbumCategory.NONE;
        this.periodicity = Period.parse("p0y0m0d");
        this.issue = -1;
        this.journalismTopic = JournalismTopic.NONE;
        this.geographicalScope = GeographicalScope.NONE;
    }


    public Filter publicationType(String publicationType) {
        this.publicationType = publicationType;
        return this;
    }

    public Filter author(String author) {
        this.author = author;
        return this;
    }

    public Filter title(String title) {
        this.title = title;
        return this;
    }

    public Filter publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Filter language(String language) {
        this.language = language;
        return this;
    }

    public Filter isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Filter bookCategory(List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
        return this;
    }

    public Filter albumCategory(AlbumCategory albumCategory){
        this.albumCategory = albumCategory;
        return this;
    }

    public Filter Period(Period period){
        this.periodicity = period;
        return this;
    }

    public Filter issue(int issue) {
        this.issue(issue);
        return this;
    }

    public Filter journalismTopic(JournalismTopic topic){
        this.journalismTopic = topic;
        return this;
    }

    public Filter geographicalScope(GeographicalScope scope){
        this.geographicalScope = scope;
        return this;
    }

    //getters
    public String getPublicationType() {
        return publicationType;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<BookCategory> getBookCategory() {
        return bookCategory;
    }

    public AlbumCategory getAlbumCategory() {
        return albumCategory;
    }

    public Period getPeriodicity() {
        return periodicity;
    }

    public int getIssue() {
        return issue;
    }

    public JournalismTopic getJournalismTopic() {
        return journalismTopic;
    }

    public GeographicalScope getGeographicalScope() {
        return geographicalScope;
    }
}
