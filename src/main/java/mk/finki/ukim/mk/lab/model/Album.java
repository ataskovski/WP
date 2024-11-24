package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    private List<Song> songs;

    public Album(String name, String genre, String releaseYear) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songs = new ArrayList<>();
    }

    public void addSongToAlbum(Song s){
        if(!songs.contains(s)){
            songs.add(s);
        }
    }
}
