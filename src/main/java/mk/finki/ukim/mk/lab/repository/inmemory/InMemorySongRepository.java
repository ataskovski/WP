package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemorySongRepository {
    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(s -> s.getTrackID().equals(trackId)).findFirst().orElse(null);
    }

    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst();
    }

    public void deleteById(Long id) {
        Song songToDelete = DataHolder.songs.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst().orElse(null);
        if (songToDelete != null) {
            DataHolder.songs.remove(songToDelete);
        }
    }

    public Optional<Song> save(String trackID, String title, String genre, int releaseYear, Long albumId) {
        Album a = DataHolder.albums.stream().filter(album -> album.getId().equals(albumId)).findFirst().orElse(null);
        if (a == null) {
            throw new IllegalArgumentException();
        }
        Song s = new Song(trackID, title, genre, releaseYear,a);
        s.addAlbum(a);
        a.addSongToAlbum(s);
        DataHolder.songs.removeIf(song -> song.getTitle().equals(s.getTitle()));
        DataHolder.songs.add(s);
        return Optional.of(s);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        Song s1 = DataHolder.songs.stream().filter(s -> s.getTrackID().equals(song.getTrackID())).findFirst().orElse(null);
        if (s1 != null) {
            s1.addArtist(artist);
            return artist;
        }
        return null;
    }
}
