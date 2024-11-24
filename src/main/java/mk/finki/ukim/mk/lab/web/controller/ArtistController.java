package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    public final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String getArtistPage(@RequestParam String songItem, Model model){
        model.addAttribute("trackID", songItem);
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        return "artistsList";
    }
}
