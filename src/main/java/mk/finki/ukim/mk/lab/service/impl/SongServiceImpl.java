package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.jpa.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    public final SongRepository songRepository;
    public final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        //return songRepository.addArtistToSong(artist, song);
        return null;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackID(trackId);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> save(String trackID, String title, String genre, int releaseYear, Long albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        if(album.isEmpty()){
            return Optional.empty();
        }
//        if (songRepository.findById(id).isPresent()) {
//            songRepository.deleteById(id);
//        }
        return Optional.of(songRepository.save(new Song(trackID, title, genre, releaseYear, album.get())));
    }

    @Override
    public Optional<Song> update(Long id, String trackID, String title, String genre, int releaseYear, Long albumID) {
        Song song = songRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        Album album = albumRepository.findById(albumID)
                        .orElseThrow(IllegalArgumentException::new);
        song.setTrackID(trackID);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        return Optional.of(this.songRepository.save(song));
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songRepository.findAll().stream().filter(song -> song.getAlbum().getId().equals(albumId)).toList();
    }
}
