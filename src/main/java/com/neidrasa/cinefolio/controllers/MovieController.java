package com.neidrasa.cinefolio.controllers;

import com.neidrasa.cinefolio.models.Movie;
import com.neidrasa.cinefolio.services.MovieService;
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

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/title")
    public List<Movie> getMovieByTitle(@RequestParam(defaultValue = "") String title,
                                     @RequestParam(defaultValue = "1") int page) throws IOException, InterruptedException {
        return movieService.searchByTitle(title, page);

    }

    @GetMapping
    public List<Movie> getAllMovies(
            @RequestParam(defaultValue = "1") int page) throws IOException, InterruptedException{
        System.out.println("COUCOU JE SUIS UN ENDPOINT!");
        return movieService.getAllMovies(page);
    }

    @GetMapping("/latest")
    public List<Movie> getLatestMovies(@RequestParam(defaultValue = "1") int page) throws IOException, InterruptedException {
        return movieService.getLatestMovies(page);
    }

    @GetMapping("/popular")
    public List<Movie> getPopularMovies(@RequestParam(defaultValue = "1") int page) throws IOException, InterruptedException {
        return movieService.getPopularMovies(page);
    }

    @GetMapping("/genres")
    public List<Movie> searchByGenre(@RequestParam String[] genre,
                                     @RequestParam(defaultValue = "1") int page) throws IOException, InterruptedException {
        return movieService.searchByGenre(genre, page);
    }
}
