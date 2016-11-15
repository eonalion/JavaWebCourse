package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.Publication;

/**
 *
 */
public interface IPublicationBuilder<P extends Publication> {
    P parse(String data) throws IllegalInputDataException;
    P build();
}
