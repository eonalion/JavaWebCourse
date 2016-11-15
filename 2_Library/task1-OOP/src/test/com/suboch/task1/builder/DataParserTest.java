package test.com.suboch.task1.builder;

import com.suboch.task1.publication.builder.BookBuilder;
import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.Book;
import com.suboch.task1.publication.tag.BookCategory;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.PublicationFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 */
public class DataParserTest {
    private Book actualPublications;

    @Before
    public void createExpectedValue() {
        BookBuilder bookBuilder = new BookBuilder();
        actualPublications = bookBuilder
                .title("The Illustrated Man")
                .authors(Arrays.asList("Ray Bradbury"))
                .publisher("SIMON & SCHUSTER")
                .publicationCity("v")
                .publicationCountry("USA")
                .publicationDate(LocalDate.parse("2012-08-06"))
                .language("EN")
                .illustration(IllustrationType.NONE)
                .pageAmount(279)
                .dimensions("104x168x24mm")
                .weight(100)
                .publicationFormat(PublicationFormat.PAPERBACK)
                .isbn("200-300405-48")
                .bookCategory(Arrays.asList(BookCategory.FICTION, BookCategory.SCIENCE_FICTION_FANTASY_HORROR))
                .build();
    }

    @Test
    public void testForDataMapping() {
        BookBuilder builder = new BookBuilder();
        String book1 = "book;The Illustrated Man;Ray Bradbury;SIMON & SCHUSTER;v;USA;2012-08-06;EN;none;279;104x168x24mm;100;paperback;200-300405-48;fiction,science_fiction_fantasy_horror";

        try {
            Book expectedPublications = builder.parse(book1);
            Assert.assertTrue(expectedPublications.equals(actualPublications));
        } catch (IllegalInputDataException e){
            Assert.fail();
        }
    }
}
