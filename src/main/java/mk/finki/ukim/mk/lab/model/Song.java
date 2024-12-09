package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackID;
    private String title;
    private String genre;
    private int releaseYear;
    //private List<Artist> performers;
    @ManyToOne
    private Album album;

    public Song() {
    }

    public Song(String trackID, String title, String genre, int releaseYear, Album album) {
        this.trackID = trackID;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        //this.performers = new ArrayList<>();
        this.album = album;
    }

    public void addArtist(Artist a) {
//        if (!performers.contains(a)) {
//            performers.add(a);
//        }
    }

    public void addAlbum(Album a) {
        if (this.album == null) {
            this.album = a;
        }
    }
}
