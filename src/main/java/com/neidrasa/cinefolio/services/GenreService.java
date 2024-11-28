package com.neidrasa.cinefolio.services;

import com.neidrasa.cinefolio.models.Genre;
import com.neidrasa.cinefolio.models.GenreApiResponse;
import com.neidrasa.cinefolio.repositories.api.ApiCaller;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class GenreService {

    private final ApiCaller apiCaller;

    // Injection par constructeur
    public GenreService(ApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    // On récupère les IDs des genres
    public List<Genre> fetchGenre() throws IOException, InterruptedException {
        String endpoint = "genre/movie/list?language=fr";
        GenreApiResponse genreResponse = apiCaller.fetchFromApi(endpoint, GenreApiResponse.class);
        System.out.println(genreResponse.getGenres());
        return genreResponse.getGenres();
    }

    public List<Integer> findGenreIdsByName(String[] genreNames) throws IOException, InterruptedException {
        List<Genre> genres = fetchGenre(); // Récupère tous les genres avec leurs IDs

        // Transforme les noms des genres donnés en IDs
        return Arrays.stream(genreNames) // Convertit le tableau en un flux pour les traiter un par un
                .map(name -> // Applique cette opération pour chaque nom de genre dans le flux :
                        genres.stream() // Parcourt la liste des genres disponibles
                        .filter(genre -> genre.getName().equalsIgnoreCase(name)) // Cherche celui qui correspond au nom (insensible à la casse) La fonction dans .filter est appelée une lambda !
                        .map(Genre::getId) // Une fois trouvé, prend son ID : Appelle la méthode getId() sur chaque élément de type Genre dans le flux.
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Genre Introuvable : " + name))
                )
                .toList(); // Transforme le flux d'IDs en liste
    }
}
