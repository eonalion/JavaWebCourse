package com.suboch.task1.publication.builder;

import com.suboch.task1.exception.IllegalInputDataException;
import com.suboch.task1.publication.Album;
import com.suboch.task1.publication.tag.AlbumCategory;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class AlbumBuilder extends PublicationBuilder<Album, AlbumBuilder> {
    private final int ALBUM_CATEGORY = 13;

    public AlbumBuilder() {
        super(new Album());
    }

    public AlbumBuilder albumCategory(AlbumCategory albumCategory) {
        publication.setAlbumCategory(albumCategory);
        return this;
    }

    public Album parse(String data) throws IllegalInputDataException {
        List<String> dataList = Arrays.asList(data.split(";"));
        publication= super.parse(data);
        try {
            albumCategory(AlbumCategory.valueOf(dataList.get(ALBUM_CATEGORY).toUpperCase()));
        } catch (IndexOutOfBoundsException|IllegalArgumentException e){
            throw new IllegalInputDataException(e);
        }
        return publication;
    }

    public Album build(){
        return new Album(publication);
    }
}
