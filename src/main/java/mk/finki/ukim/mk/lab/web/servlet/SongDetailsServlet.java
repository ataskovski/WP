package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "song-details-servlet", urlPatterns = "/song/details")
public class SongDetailsServlet extends HttpServlet {
    public final SongService songService;
    public final ArtistService artistService;
    public final SpringTemplateEngine springTemplateEngine;

    public SongDetailsServlet(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artistID = req.getParameter("artistItem");
        String songID = req.getParameter("songItem");
        Song s = null;
        Artist a = null;

        if(artistID!=null && !artistID.isEmpty() && songID !=null && !songID.isEmpty()){
            s = songService.findByTrackId(songID);
            a = artistService.ArtistfindById(Long.valueOf(artistID));
            songService.addArtistToSong(a, s);
            artistService.addSongToArtist(a,s);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);
        context.setVariable("song", s);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
