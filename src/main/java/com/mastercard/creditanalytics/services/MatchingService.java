package com.mastercard.creditanalytics.services;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MatchingApi;
import org.openapitools.client.model.Match;

import java.util.List;

/**
 * Thin wrapper around the generated {@link MatchingApi}. One method per upstream call.
 */
public class MatchingService {

    private final MatchingApi matchingApi;

    public MatchingService(ApiClient apiClient) {
        this(new MatchingApi(apiClient));
    }

    /** Constructor used by unit tests to inject a mock {@link MatchingApi}. */
    public MatchingService(MatchingApi matchingApi) {
        this.matchingApi = matchingApi;
    }

    public List<Match> getMatches(String companyName,
                                  String streetAddress,
                                  String postalCode,
                                  String city,
                                  String stateProvinceCode,
                                  String countryCode,
                                  String idType,
                                  String idValue) throws ApiException {
        return matchingApi.getMatches(
                companyName,
                streetAddress,
                postalCode,
                city,
                stateProvinceCode,
                countryCode,
                idType,
                idValue
        );
    }
}
