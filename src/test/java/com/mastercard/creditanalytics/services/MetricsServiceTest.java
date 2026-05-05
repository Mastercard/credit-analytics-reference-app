package com.mastercard.creditanalytics.services;

import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MetricsApi;
import org.openapitools.client.model.MetricsPerLocation;

import java.util.UUID;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MetricsService} — verifies the wrapper passes arguments through unchanged.
 */
public class MetricsServiceTest {

    private MetricsApi metricsApi;
    private MetricsService service;

    @Before
    public void setUp() {
        metricsApi = mock(MetricsApi.class);
        service = new MetricsService(metricsApi);
    }

    @Test
    public void getMetricsDelegatesToApiAndReturnsResult() throws ApiException {
        MetricsPerLocation expected = new MetricsPerLocation();
        UUID locationId = UUID.fromString("a1b2c3d4-0000-1234-abcd-000000000001");
        when(metricsApi.getMetrics(locationId, true, "Weekly", "retail_sales_analytics")).thenReturn(expected);

        MetricsPerLocation actual = service.getMetrics(locationId, true, "Weekly", "retail_sales_analytics");

        assertSame(expected, actual);
        verify(metricsApi).getMetrics(locationId, true, "Weekly", "retail_sales_analytics");
    }
}
