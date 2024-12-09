package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {
    public List<Artist> findAll() {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream().filter(a -> a.getID() == id).findFirst();
    }

    public Song addSongToArtist(Artist artist, Song song) {
        Artist a1 = DataHolder.artists.stream().filter(a->a.getID() == artist.getID()).findFirst().orElse(null);
        if(a1 != null){
            a1.addSong(song);
            return song;
        }
        return null;
    }
}
