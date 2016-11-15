package test.com.suboch.task1.builder;

import com.suboch.task1.publication.builder.*;
import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.factory.PublicationBuilderFactory;
import com.suboch.task1.library.creator.LibraryCreator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DataFormatTest {
    @Test(expected = IllegalPublicationTypeException.class)
    public void testInputForPublicationType() throws IllegalPublicationTypeException {
        PublicationBuilderFactory factory = new PublicationBuilderFactory();
        LibraryCreator creator = new LibraryCreator();
        String parameters = "notes;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback";
        List<String> dataList = Arrays.asList(parameters.split(";"));
        creator.getBuilder(factory, dataList);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForBookParametersAmount() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;200-300405-48";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForMagazineParametersAmount() throws IllegalInputDataException {
        PublicationBuilder builder = new MagazineBuilder();
        String parameters = "magazine;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;p0y1m0d;5";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputAlbumForParametersAmount() throws IllegalInputDataException {
        PublicationBuilder builder = new AlbumBuilder();
        String parameters = "album;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputNewspaperForParametersAmount() throws IllegalInputDataException {
        PublicationBuilder builder = new NewspaperBuilder();
        String parameters = "newspaper;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;p0y1m0d;5;sports";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationDateFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;?????;language;color;200;150/90/30mm;250;paperback;45788-76-77;fiction";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationIllustrationTypeFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;1995-01-13;language;?????;200;150/90/30mm;250;paperback;45788-76-77;fiction";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationPageAmountFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;1995-01-13;language;color;????;150/90/30mm;250;paperback;45788-76-77;fiction";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationWeightFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;1995-01-13;language;color;250;150/90/30mm;???;paperback;45788-76-77;fiction";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationCoverFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;1995-01-13;language;color;250;150/90/30mm;200;???;45788-76-77;fiction";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForSerialPublicationPeriodFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new MagazineBuilder();
        String parameters = "magazine;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;????;5;showbiz";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForSerialPublicationIssueFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new MagazineBuilder();
        String parameters = "magazine;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;p0y1m0d;?????;showbiz";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForBookCategoryFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new BookBuilder();
        String parameters = "book;title;author1,author2;publisher;city;country;1995-01-13;language;color;250;150/90/30mm;200;paperback;45788-76-77;?????";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForPublicationJournalismTopicFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new MagazineBuilder();
        String parameters = "magazine;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;p0y1m0d;5;not_existed_topic";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForAlbumCategoryFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new AlbumBuilder();
        String parameters = "album;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;not_existed_category";
        builder.parse(parameters);
    }

    @Test(expected = IllegalInputDataException.class)
    public void testInputForNewspaperGeographicalScopeFormat() throws IllegalInputDataException {
        PublicationBuilder builder = new NewspaperBuilder();
        String parameters = "newspaper;title;author1,author2;publisher;city;country;0000-01-01;language;color;200;150/90/30mm;250;paperback;p0y1m0d;5;sports;not_existed_scope";
        builder.parse(parameters);
    }
}
