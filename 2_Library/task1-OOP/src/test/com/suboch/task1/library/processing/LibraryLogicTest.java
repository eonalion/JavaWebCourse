package test.com.suboch.task1.library.processing;

import com.suboch.task1.publication.builder.BookBuilder;
import com.suboch.task1.library.Library;
import com.suboch.task1.library.processing.LibraryProcessing;
import com.suboch.task1.publication.PublicationEnum;
import com.suboch.task1.publication.tag.BookCategory;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.PublicationFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

/**
 *
 */
public class LibraryLogicTest {
    BookBuilder builder;
    LibraryProcessing libraryProcessing;
    Library library;
    Random rand;

    @Before
    public void initialize() {
        libraryProcessing = new LibraryProcessing();
        library = new Library();
        builder = new BookBuilder();
        rand = new Random();

        builder.title("Miss Peregrine's Home for Peculiar Children")
                .authors(Arrays.asList("Ransom Riggs"))
                .publisher("Quirk Books")
                .publicationCity("Philadelphia")
                .publicationCountry("USA")
                .publicationDate(LocalDate.parse("2012-08-13"))
                .language("EN")
                .illustration(IllustrationType.BLACK_AND_WHITE)
                .dimensions("130x206x26mm")
                .weight(500)
                .publicationFormat(PublicationFormat.HARDBACK)
                .bookCategory(Arrays.asList(BookCategory.CRIME_AND_THRILLER, BookCategory.SCIENCE_FICTION_FANTASY_HORROR));
    }

    private int randomPageAmount() {
        return rand.nextInt((2000 - 5) + 1) + 5;
    }

    @Test
    public void testForPublicationsPageAmount() {
        long expectedTotalPageAmount = 0;
        int newPageAmount;

        for (int i = 0; i < 20; i++) {
            newPageAmount = randomPageAmount();
            expectedTotalPageAmount += newPageAmount;
            library.addItem(builder.pageAmount(newPageAmount).build());
        }

        long actualTotalPageAmount = libraryProcessing.publicationPageAmount(PublicationEnum.BOOK, library);
        Assert.assertEquals(expectedTotalPageAmount, actualTotalPageAmount);
    }
}
