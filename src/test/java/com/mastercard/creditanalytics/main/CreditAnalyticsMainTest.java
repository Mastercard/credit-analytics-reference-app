package com.mastercard.creditanalytics.main;

import com.mastercard.creditanalytics.examples.MatchesAndMetricsFlowExample;
import com.mastercard.creditanalytics.examples.MatchingFlowExample;
import com.mastercard.creditanalytics.examples.MetricsFlowExample;
import com.mastercard.creditanalytics.services.MatchingService;
import com.mastercard.creditanalytics.services.MetricsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Match;
import org.openapitools.client.model.MetricsPerLocation;

import java.util.Collections;
import java.util.UUID;

import static com.mastercard.creditanalytics.utils.ExampleConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * End-to-end smoke test for {@link CreditAnalyticsMain#main(String[])} with both upstream services mocked.
 * Stubs are configured so every {@code throws*} flow actually throws, exercising the catch blocks in main.
 */
public class CreditAnalyticsMainTest {

    private MatchingService matchingService;
    private MetricsService metricsService;

    @Before
    public void setUp() throws Exception {
        Match match = new Match();
        match.setLocationId(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID));

        matchingService = mock(MatchingService.class);
        metricsService = mock(MetricsService.class);

        when(matchingService.getMatches(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(Collections.singletonList(match));
        when(metricsService.getMetrics(any(UUID.class), any(), any(), any()))
                .thenReturn(new MetricsPerLocation());

        ApiException matchEx = new ApiException("matching error");
        when(matchingService.getMatches(any(), any(), any(), any(), any(),
                eq(USA_COUNTRY_CODE), eq(EXAMPLE_INVALID_ID_TYPE), any())).thenThrow(matchEx);
        when(matchingService.getMatches(any(), any(), any(), any(), any(),
                eq(USA_COUNTRY_CODE), eq(EXAMPLE_ID_TYPE_MERCHANT_ID), eq(NO_MATCH_ID_VALUE))).thenThrow(matchEx);
        when(matchingService.getMatches(any(), any(), any(), any(), any(),
                eq(USA_COUNTRY_CODE), eq(EXAMPLE_ID_TYPE_MERCHANT_ID), eq(EXAMPLE_AGG_MERCHANT_ID_VALUE))).thenThrow(matchEx);
        when(matchingService.getMatches(eq(NO_MATCH_COMPANY_NAME), any(), any(), any(), any(),
                any(), any(), any())).thenThrow(matchEx);
        when(matchingService.getMatches(eq(AGG_MERCHANT_COMPANY_EXAMPLE), any(), any(), any(), any(),
                any(), any(), any())).thenThrow(matchEx);
        when(matchingService.getMatches(any(), any(), eq("105776"), any(), any(),
                any(), any(), any())).thenThrow(matchEx);
        when(matchingService.getMatches(any(), any(), any(), any(), eq("NA"),
                any(), any(), any())).thenThrow(matchEx);
        when(matchingService.getMatches(any(), any(), any(), any(), any(),
                eq("AUS"), any(), any())).thenThrow(matchEx);

        ApiException metricsEx = new ApiException("metrics error");
        when(metricsService.getMetrics(eq(UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID)),
                any(), any(), any())).thenThrow(metricsEx);
        when(metricsService.getMetrics(eq(UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID)),
                any(), any(), any())).thenThrow(metricsEx);
        when(metricsService.getMetrics(any(UUID.class), eq(false), any(), any())).thenThrow(metricsEx);
        when(metricsService.getMetrics(any(UUID.class), any(), eq(null), any())).thenThrow(metricsEx);

        MatchingFlowExample.setService(matchingService);
        MetricsFlowExample.setService(metricsService);
    }

    @After
    public void tearDown() {
        MatchingFlowExample.setService(null);
        MetricsFlowExample.setService(null);
    }

    @Test
    public void mainRunsEveryFlowToCompletion() throws Exception {
        CreditAnalyticsMain.main(new String[0]);

        verify(matchingService, atLeastOnce()).getMatches(any(), any(), any(), any(), any(), any(), any(), any());
        verify(metricsService, atLeastOnce()).getMetrics(any(UUID.class), any(), any(), any());
    }

    @Test
    public void mainCanBeInstantiated() {
        new CreditAnalyticsMain();
    }

    @Test
    public void combinedFlowsAreReachable() throws Exception {
        MatchesAndMetricsFlowExample.getMetricsWeeklyUsingMatchResults();
        MatchesAndMetricsFlowExample.getMetricsMonthlyUsingMatchResults();
        MatchesAndMetricsFlowExample.getBenchmarksMetricsUsingMatchResults();
    }
}
