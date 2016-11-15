package com.suboch.task1.library.processing;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.library.Library;
import com.suboch.task1.library.creator.LibraryCreator;
import com.suboch.task1.library.search.PublicationSearcher;
import com.suboch.task1.library.search.PublicationSearcherCreator;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.Publication;
import com.suboch.task1.publication.PublicationEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class LibraryProcessing {
    private static Logger logger = LogManager.getLogger();

    public Library createLibrary() {
        Library library = new Library();
        try {
            LibraryCreator creator = new LibraryCreator();
            library = creator.create();
        } catch (IllegalInputDataException e) {
            logger.log(Level.ERROR, "Wrong input data", e);
        }
        return library;
    }

    public long publicationPageAmount(PublicationEnum publication, Library library) {
        long pageAmount = 0;
        String publicationType = String.valueOf(publication);

        for (Publication p : library.getPublications()) {
            if (publicationType.equalsIgnoreCase(String.valueOf(p.getClass().getSimpleName()))) {
                pageAmount += p.getPageAmount();
            }
        }
        return pageAmount;
    }

    public void sortPublicationsByPageAmount(Library library) {
        library.getPublications().sort(Comparator.comparing(Publication::getPageAmount));
    }

    public List<Publication> searchPublications(Library library, Filter filter) throws IllegalPublicationTypeException {
        PublicationSearcherCreator creator = new PublicationSearcherCreator();
        PublicationSearcher searcher = creator.create(filter.getPublicationType());
        return searcher.search(library, filter);
    }

    public void printLibrary(Library library) {
        library.forEach(n -> logger.log(Level.DEBUG, n.toString()));
    }

    public void clearLibrary(Library library) {
        library.removeAllPublications();
    }

}
