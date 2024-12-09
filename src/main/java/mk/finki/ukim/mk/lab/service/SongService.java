package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Optional<Song> findById(Long id);
    void deleteById(Long id);
    Optional<Song> save(String trackID, String title, String genre, int releaseYear, Long albumID);
    Optional<Song> update(Long id, String trackID, String title, String genre, int releaseYear, Long albumID);
    List<Song> findAllByAlbum_Id(Long albumId);
}
