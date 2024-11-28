package com.neidrasa.cinefolio.repositories.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiCaller {

    private final String apiUrl;
    private final String apiToken;

    public ApiCaller(@Value("${api.tmdb.url}") String apiUrl,
                     @Value("${api.tmdb.token}") String apiToken) {
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
    }

    public <T> T fetchFromApi(String endpoint, Class<T> responseType) throws IOException, InterruptedException {
        // Étape 1 : Construire l'URL finale
        String url = apiUrl + endpoint;

        // Étape 2 : Construire la requête HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json") // Demande de réponse JSON
                .header("Authorization", "Bearer " + apiToken) // Demande de réponse JSON
                .method("GET", HttpRequest.BodyPublishers.noBody()) // Méthode GET sans corps
                .build();

        // Étape 3 : Envoyer la requête et récupérer la réponse
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println("Réponse de l'API: " + response.body());

        // Étape 4 : Mapper la réponse JSON en objet Java du type demandé (responseType)
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), responseType);
    }




}
