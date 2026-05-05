package com.mastercard.creditanalytics.services;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MetricsApi;
import org.openapitools.client.model.MetricsPerLocation;

import java.util.UUID;

/**
 * Thin wrapper around the generated {@link MetricsApi}. One method per upstream call.
 */
public class MetricsService {

    private final MetricsApi metricsApi;

    public MetricsService(ApiClient apiClient) {
        this(new MetricsApi(apiClient));
    }

    /** Constructor used by unit tests to inject a mock {@link MetricsApi}. */
    public MetricsService(MetricsApi metricsApi) {
        this.metricsApi = metricsApi;
    }

    public MetricsPerLocation getMetrics(UUID locationId,
                                         Boolean hasConsent,
                                         String metricsFrequency,
                                         String metricsType) throws ApiException {
        return metricsApi.getMetrics(locationId, hasConsent, metricsFrequency, metricsType);
    }
}
