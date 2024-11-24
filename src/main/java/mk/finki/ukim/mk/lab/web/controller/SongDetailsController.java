package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/song-details")
public class SongDetailsController {
    public final SongService songService;
    public final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @PostMapping
    public String getSongDetails(@RequestParam String songItem,
                                 @RequestParam long artistItem,
                                 Model model) {
        Artist a = artistService.ArtistfindById(artistItem);
        Song s = songService.findByTrackId(songItem);

        if (a != null && s != null) {
            a.addSong(s);
            s.addArtist(a);
        }

        model.addAttribute("song", s);

        return "songDetails";
    }
}
