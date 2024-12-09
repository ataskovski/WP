package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    public final AlbumService albumService;
    public final SongService songService;

    public AlbumController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String getAlbumsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("showDetails", false);
        return "albums";
    }

    @GetMapping("/show-details/{id}")
    public String getSongsInAlbum(@PathVariable Long id, Model model) {

        model.addAttribute("showDetails", true);

        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);

        List<Song> songsInAlbum = songService.findAllByAlbum_Id(id);
        albumService.findById(id).ifPresent(album -> model.addAttribute("albumToShow", album));

        model.addAttribute("songs", songsInAlbum);

        return "albums";
    }
}
