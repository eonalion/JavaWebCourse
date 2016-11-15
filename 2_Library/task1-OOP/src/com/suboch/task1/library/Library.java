package com.suboch.task1.library;

import com.suboch.task1.publication.Publication;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 */
public class Library {
    private List<Publication> publications;

    public Library() {
        publications = new ArrayList<>();
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public boolean addItem(Publication newPublication) {
        return publications.add(newPublication);
    }

    public int size() {
        return this.publications.size();
    }

    public void forEach(Consumer<? super Publication> consumer) {
        publications.forEach(consumer);
    }

    public void removeAllPublications() {
        this.publications.clear();
    }
}
