package com.neidrasa.cinefolio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neidrasa.cinefolio.models.Movie;
import com.neidrasa.cinefolio.models.MovieApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class TmdbAPI {

    @Value("${api.tmdb.url}")
    private String apiUrl;

    @Value("${api.tmdb.token}")
    private String apiToken;


    public List<Movie> getAllMovies(int page, int size, String sortBy) throws IOException, InterruptedException {
        // Appel API
        String url = apiUrl + "discover/movie?include_adult=false&include_video=false&language=en-US"
                    + "&page=" + page
                    + "&sort_b=" + sortBy;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiToken)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Réponse de l'API: " + response.body());

        // Mapper le JSON en objet
        ObjectMapper objectMapper = new ObjectMapper();
        MovieApiResponse apiResponse = objectMapper.readValue(response.body(), MovieApiResponse.class);

        // Retourne la liste de films
        return apiResponse.getResults();
    }
}