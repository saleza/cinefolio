package com.neidrasa.cinefolio.controllers;

import com.neidrasa.cinefolio.models.Movie;
import com.neidrasa.cinefolio.services.TmdbAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final TmdbAPI tmdbAPI;

    @Autowired
    public MovieController(TmdbAPI tmdbAPI) {
        this.tmdbAPI = tmdbAPI;
    }

  /*  @GetMapping("/title")
    public String getMovieTitle() {
        try {
            return tmdbAPI.getMovieData();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des données du film";
        }
    }
*/
    @GetMapping
    public List<Movie> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "popularity.desc") String sortBy) throws IOException, InterruptedException{
        System.out.println("COUCOU JE SUIS UN ENDPOINT!");
        return tmdbAPI.getAllMovies(page, size, sortBy);
    }
}
