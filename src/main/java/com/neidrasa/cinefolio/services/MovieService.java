package com.neidrasa.cinefolio.services;


import com.neidrasa.cinefolio.models.GenreApiResponse;
import com.neidrasa.cinefolio.models.Movie;
import com.neidrasa.cinefolio.models.MovieApiResponse;
import com.neidrasa.cinefolio.repositories.api.ApiCaller;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final ApiCaller apiCaller;
    private final GenreService genreService;

    public MovieService(ApiCaller apiCaller, GenreService genreService) {
        this.apiCaller = apiCaller;
        this.genreService = genreService;
    }

    public List<Movie> getAllMovies(int page) throws IOException, InterruptedException {
        String endpoint = "discover/movie?include_adult=false&include_video=false&language=fr-FR"
                + "&page=" + page;

        MovieApiResponse apiResponse = apiCaller.fetchFromApi(endpoint, MovieApiResponse.class);
        System.out.println("Métier getAllMovies: " + apiResponse.getResults());
        return apiResponse.getResults();
    }

    public List<Movie> searchByTitle(String title, int page) throws IOException, InterruptedException {
        // Encodage du titre pour les espaces et caractères spéciaux
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

        String endpoint = "search/movie?query=" + encodedTitle + "&include_adult=false&language=fr-FR"
                + "&page=" + page;

        MovieApiResponse apiResponse = apiCaller.fetchFromApi(endpoint, MovieApiResponse.class);
        System.out.println("Métier getAllMovies: " + apiResponse.getResults());
        return apiResponse.getResults();
    }

    public List<Movie> getLatestMovies(int page) throws IOException, InterruptedException {
        String endpoint = "discover/movie?include_adult=false&include_video=false&language=fr-FR&sort_by=primary_release_date.desc"
                + "&page=" + page
                + "&primary_release_date.lte=" + LocalDate.now();

        MovieApiResponse apiResponse = apiCaller.fetchFromApi(endpoint, MovieApiResponse.class);
        System.out.println("Métier getLatestMovies " + apiResponse.getResults());
        return apiResponse.getResults();
    }

    public List<Movie> getPopularMovies(int page) throws IOException, InterruptedException {
        String endpoint = "discover/movie?include_adult=false&include_video=false&language=fr-FR&sort_by=popularity.desc"
                + "&page=" + page;

        MovieApiResponse apiResponse = apiCaller.fetchFromApi(endpoint, MovieApiResponse.class);
        System.out.println("Métier getPopularMovies: " + apiResponse.getResults());
        return apiResponse.getResults();
    }

    public List<Movie> searchByGenre(String[] genre, int page) throws IOException, InterruptedException {
        // Etape 1 : On récupère les IDs des genres
        List<Integer> genreIds = genreService.findGenreIdsByName(genre);

        // Etape 2 : Construire l'URL pour récupérer les films
        String endpoint = "discover/movie?include_adult=false&include_video=false&language=fr-FR&sort_by=popularity.desc&with_genres="
                + String.join("%2C", // Combine tous les éléments de la liste avec une délimitation -> "28%2C35"
                genreIds.stream() // On parcourt tous les IDs (par exemple [28, 35])
                .map(String::valueOf) // Convertit chaque ID en chaîne de caractères (par exemple "28", "35")
                .toList()) // Transforme le flux en liste (["28", "35"])
                + "&page=" + page;

        // Appeler l'API pour récupérer les films
        MovieApiResponse movieResponse = apiCaller.fetchFromApi(endpoint, MovieApiResponse.class);
        return movieResponse.getResults();
    }
}