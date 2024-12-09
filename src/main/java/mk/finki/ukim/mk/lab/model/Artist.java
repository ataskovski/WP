package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Artist {
    private long ID;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Song> songs;

    public Artist(long ID, String firstName, String lastName, String bio) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song s){
        if(!songs.contains(s)){
            songs.add(s);
        }
    }
}
