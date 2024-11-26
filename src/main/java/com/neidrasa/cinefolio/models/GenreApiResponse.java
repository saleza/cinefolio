package com.neidrasa.cinefolio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreApiResponse {

    private int page;
    private List<Genre> genres;

    public GenreApiResponse() {
    }

    public GenreApiResponse(int page, List<Genre> genres) {
        this.page = page;
        this.genres = genres;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}