package com.suboch.task1.reporter;

import com.suboch.task1.library.Library;
import com.suboch.task1.library.processing.LibraryProcessing;
import com.suboch.task1.publication.Publication;
import com.suboch.task1.publication.PublicationEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 *
 */
public class Reporter {
    private static Logger logger = LogManager.getLogger(Reporter.class);

    public void printPageAmount(PublicationEnum publication, long pageAmount) {
        String publicationType = String.valueOf(publication);
        logger.log(Level.DEBUG, publicationType + " " + "page amount:\t" + pageAmount + "\n");
    }

    public void printLibrary(LibraryProcessing libraryProcessing, Library library) {
        libraryProcessing.printLibrary(library);
    }

    public void printSearchResult(List<Publication> publicationList) {
        publicationList.forEach(p -> logger.log(Level.DEBUG, p));
    }

    public void printMessage(String message){
        logger.log(Level.DEBUG, message+"\n");
    }
}
