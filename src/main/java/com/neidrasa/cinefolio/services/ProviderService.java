package com.neidrasa.cinefolio.services;

import com.neidrasa.cinefolio.models.Provider;
import com.neidrasa.cinefolio.models.ProviderApiResponse;
import com.neidrasa.cinefolio.repositories.api.ApiCaller;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ProviderService {

    private final ApiCaller apiCaller;

    public ProviderService(ApiCaller apiCaller) {
        this.apiCaller = apiCaller;
    }

    public List<Provider> fetchProvider() throws IOException, InterruptedException {
        String endpoint = "watch/providers/movie?language=fr-FR&watch_region=FR";
        ProviderApiResponse providerResponse = apiCaller.fetchFromApi(endpoint, ProviderApiResponse.class);
        System.out.println("Fetched Providers: " + providerResponse.getProviders());
        return providerResponse.getProviders();
    }

    public List<Integer> findProviderIdByName(String[] providerNames) throws IOException, InterruptedException {
        List<Provider> providers = fetchProvider();
        System.out.println("Providers :" + providers);

        return Arrays.stream(providerNames)
                .map(name ->
                        providers.stream()
                                .filter(provider -> {
                                    String normalizedName = name.replaceAll("\\s","").toLowerCase();
                                    String providerName = provider.getProvider_name().replaceAll("\\s", "").toLowerCase();
                                    return providerName.equalsIgnoreCase(normalizedName);
                                })
                                .map(Provider::getProvider_id)
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Plateformes introuvable : " + name))
                )
                .toList();
    }
}
