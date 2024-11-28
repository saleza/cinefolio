package com.neidrasa.cinefolio.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int provider_id;
    private String provider_name;
    private String logo_path;
}
