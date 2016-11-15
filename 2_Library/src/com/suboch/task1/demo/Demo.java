package com.suboch.task1.demo;

import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.library.Library;
import com.suboch.task1.library.processing.LibraryProcessing;
import com.suboch.task1.library.search.filter.Filter;
import com.suboch.task1.publication.PublicationEnum;
import com.suboch.task1.publication.tag.BookCategory;
import com.suboch.task1.reporter.Reporter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 *
 */
public class Demo {
    private static Logger logger = LogManager.getLogger(Demo.class);

    private static final String FILTER1_TYPE = "book";
    private static final String FILTER1_AUTHOR = "Ray Bradbury";
    private static final String FILTER1_TITLE = "The Illustrated Man";
    private static final String FILTER1_ISBN = "200-300405-48";
    private static final String FILTER2_TYPE = "all";
    private static final String FILTER2_LANGUAGE= "EN";

    public static void main(String[] args) {
        Reporter reporter = new Reporter();
        LibraryProcessing libraryProcessing = new LibraryProcessing();
        Library library = libraryProcessing.createLibrary();
        reporter.printMessage("Library contains:");
        reporter.printLibrary(libraryProcessing, library);

        //Count total page amount of publication type
        long totalAlbumPageAmount = libraryProcessing.publicationPageAmount(PublicationEnum.NEWSPAPER, library);
        System.out.println();
        reporter.printPageAmount(PublicationEnum.NEWSPAPER, totalAlbumPageAmount);

        //Sort publications by page amount
        reporter.printMessage("Library sort[by page amount] result:");
        libraryProcessing.sortPublicationsByPageAmount(library);
        reporter.printLibrary(libraryProcessing, library);

        //Create filter for advanced search and show result
        Filter filter1 = new Filter();
        filter1.publicationType(FILTER1_TYPE)
                .author(FILTER1_AUTHOR)
                .title(FILTER1_TITLE)
                .isbn(FILTER1_ISBN)
                .bookCategory(Arrays.asList(BookCategory.SCIENCE_FICTION_FANTASY_HORROR, BookCategory.FICTION));

        //Create filter for advanced search and show result
        Filter filter2 = new Filter();
        filter2.publicationType(FILTER2_TYPE)
                .language(FILTER2_LANGUAGE);

        System.out.println();

        try {
            reporter.printMessage("Advanced publication search result["+FILTER1_TYPE+","+FILTER1_AUTHOR+","+FILTER1_TITLE+","+FILTER1_ISBN+"]");
            reporter.printSearchResult(libraryProcessing.searchPublications(library, filter1));
            System.out.println();
            reporter.printMessage("Advanced publication search result["+FILTER2_TYPE+","+FILTER2_LANGUAGE+"]");
            reporter.printSearchResult(libraryProcessing.searchPublications(library, filter2));
        } catch (IllegalPublicationTypeException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
