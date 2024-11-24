package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    private Long id;
    private String trackID;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Album album;

    public Song(String trackID, String title, String genre, int releaseYear) {
        this.id = (long) (Math.random() * 1000);
        this.trackID = trackID;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = null;
    }

    public void addArtist(Artist a) {
        if (!performers.contains(a)) {
            performers.add(a);
        }
    }

    public void addAlbum(Album a) {
        if (this.album == null) {
            this.album = a;
        }
    }
}
