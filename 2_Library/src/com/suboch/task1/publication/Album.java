package com.suboch.task1.publication;

import com.suboch.task1.publication.tag.AlbumCategory;
import com.suboch.task1.publication.tag.IllustrationType;
import com.suboch.task1.publication.tag.PublicationFormat;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class Album extends Publication{
    private AlbumCategory albumCategory;

    public Album(String title, List<String> authors, String publisher, String publicationCity, String publicationCountry, LocalDate publicationDate, String language, IllustrationType illustrations, int pageAmount, String dimensions, int weight, PublicationFormat format, int ID, AlbumCategory albumCategory) {
        super(title, authors, publisher, publicationCity, publicationCountry, publicationDate, language, illustrations, pageAmount, dimensions, weight, format, ID);
        this.albumCategory = albumCategory;
    }

    public Album(Album album){
        super(album);
        this.albumCategory = album.getAlbumCategory();
    }

    public Album(){
        this.albumCategory = AlbumCategory.NONE;
    }

    public AlbumCategory getAlbumCategory() {
        return albumCategory;
    }

    public void setAlbumCategory(AlbumCategory albumCategory) {
        this.albumCategory = albumCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Album album = (Album) o;

        return albumCategory == album.albumCategory;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (albumCategory != null ? albumCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                super.toString()+
                ", albumCategory=" + String.valueOf(albumCategory).toLowerCase() +
                '}';
    }
}
