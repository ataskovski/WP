package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (artists.isEmpty()) {
            artists.add(new Artist(1L, "ArtistName1", "ArtistSurname1", "artist1 bio"));
            artists.add(new Artist(2L, "ArtistName2", "ArtistSurname2", "artist2 bio"));
            artists.add(new Artist(3L, "ArtistName3", "ArtistSurname3", "artist3 bio"));
            artists.add(new Artist(4L, "ArtistName4", "ArtistSurname4", "artist4 bio"));
            artists.add(new Artist(5L, "ArtistName5", "ArtistSurname5", "artist5 bio"));
        }

        if (albums.isEmpty()) {
            Album album1 = new Album("Album1", "rap", "2010");
            Album album2 = new Album("Album2", "rock", "1996");
            Album album3 = new Album("Album3", "pop", "2015");
            Album album4 = new Album("Album4", "folk", "2002");
            Album album5 = new Album("Album5", "rap", "2024");
            albums.add(album1);
            albums.add(album2);
            albums.add(album3);
            albums.add(album4);
            albums.add(album5);
        }
    }
}
