package com.neidrasa.cinefolio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderApiResponse {

    @JsonProperty("results")
    private List<Provider> results;

    public ProviderApiResponse() {
    }

    public ProviderApiResponse(List<Provider> results) {
        this.results = results;
    }

    public List<Provider> getProviders() {
        return results;
    }

    public void setProviders(List<Provider> results) {
        this.results = results;
    }
}
