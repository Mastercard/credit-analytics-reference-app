package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.services.MatchingService;
import com.mastercard.creditanalytics.services.MetricsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.model.Match;
import org.openapitools.client.model.MetricsPerLocation;

import java.util.Collections;
import java.util.UUID;

import static com.mastercard.creditanalytics.utils.ExampleConstants.BENCHMARKS_METRICS_QUERY_PARAM;
import static com.mastercard.creditanalytics.utils.ExampleConstants.MONTHLY;
import static com.mastercard.creditanalytics.utils.ExampleConstants.RSA_METRICS_QUERY_PARAM;
import static com.mastercard.creditanalytics.utils.ExampleConstants.WEEKLY;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MatchesAndMetricsFlowExample}, exercising the combined match-then-metrics flow
 * with both upstream services mocked.
 */
public class MatchesAndMetricsFlowExampleTest {

    private MatchingService matchingService;
    private MetricsService metricsService;
    private final UUID locationId = UUID.fromString("a1b2c3d4-0000-1234-abcd-000000000001");
    private final MetricsPerLocation metricsStub = new MetricsPerLocation();

    @Before
    public void setUp() throws Exception {
        Match match = new Match();
        match.setLocationId(locationId);
        matchingService = mock(MatchingService.class);
        metricsService = mock(MetricsService.class);
        when(matchingService.getMatches(any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(Collections.singletonList(match));
        when(metricsService.getMetrics(any(UUID.class), any(), any(), any())).thenReturn(metricsStub);
        MatchingFlowExample.setService(matchingService);
        MetricsFlowExample.setService(metricsService);
    }

    @After
    public void tearDown() {
        MatchingFlowExample.setService(null);
        MetricsFlowExample.setService(null);
    }

    @Test
    public void getMetricsWeeklyUsingMatchResults_passesLocationIdFromFirstMatch() throws Exception {
        assertSame(metricsStub, MatchesAndMetricsFlowExample.getMetricsWeeklyUsingMatchResults());
        verify(metricsService).getMetrics(locationId, true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMetricsMonthlyUsingMatchResults_passesLocationIdFromFirstMatch() throws Exception {
        assertSame(metricsStub, MatchesAndMetricsFlowExample.getMetricsMonthlyUsingMatchResults());
        verify(metricsService).getMetrics(locationId, true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getBenchmarksMetricsUsingMatchResults_passesLocationIdFromFirstMatch() throws Exception {
        assertSame(metricsStub, MatchesAndMetricsFlowExample.getBenchmarksMetricsUsingMatchResults());
        verify(metricsService).getMetrics(locationId, true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }
}
