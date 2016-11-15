package com.suboch.task1.library.creator;

import com.suboch.task1.publication.builder.PublicationBuilder;
import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.exception.IllegalPublicationTypeException;
import com.suboch.task1.factory.PublicationBuilderFactory;
import com.suboch.task1.library.Library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class LibraryCreator {
    private static String filePath = "data/data.txt";

    private final int PUBLICATION_TYPE = 0;

    private List<String> lines = new ArrayList<>();

    public Library create() throws IllegalInputDataException {
        PublicationBuilderFactory factory = new PublicationBuilderFactory();
        Library library = new Library();
        List<String> dataList;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            lines = stream
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
            for (String s: lines) {
                dataList = Arrays.asList(s.split(";"));
                library.addItem(getBuilder(factory, dataList).parse(s));
            }
        } catch (IOException | IllegalArgumentException | IllegalPublicationTypeException e) {
            throw new IllegalInputDataException(e);
        }

        return library;
    }

    public PublicationBuilder getBuilder(PublicationBuilderFactory factory, List<String> dataList) throws IllegalPublicationTypeException {
        return factory.newPublicationBuilder(dataList.get(PUBLICATION_TYPE));
    }
}
