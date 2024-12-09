package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    public final SongService songService;
    public final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Song> songs = this.songService.listSongs();
        model.addAttribute("songs", songs);
        return "listSongs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        if (songService.findById(id).isPresent()) {
            songService.deleteById(id);
        }
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        if (this.songService.findById(id).isPresent()) {
            Song song = this.songService.findById(id).get();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("song", song);
            model.addAttribute("albums", albums);
            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }



    @PostMapping("/add")
    public String saveSong(@RequestParam(required = false) Long id,
                           @RequestParam String trackID,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int release_year,
                           @RequestParam Long album) {
        if (id != null) {
            this.songService.update(id, trackID, title, genre, release_year, album);
        } else {
            this.songService.save(trackID, title, genre, release_year, album);
        }

        return "redirect:/songs";
    }

}
