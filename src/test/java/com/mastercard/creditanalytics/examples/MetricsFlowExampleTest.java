package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.services.MetricsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.model.MetricsPerLocation;

import java.util.UUID;

import static com.mastercard.creditanalytics.utils.ExampleConstants.*;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for every static flow in {@link MetricsFlowExample}, using a mock {@link MetricsService}.
 */
public class MetricsFlowExampleTest {

    private MetricsService metricsService;
    private final MetricsPerLocation stub = new MetricsPerLocation();

    @Before
    public void setUp() throws Exception {
        metricsService = mock(MetricsService.class);
        when(metricsService.getMetrics(any(UUID.class), any(), any(), any())).thenReturn(stub);
        MetricsFlowExample.setService(metricsService);
    }

    @After
    public void tearDown() {
        MetricsFlowExample.setService(null);
    }

    /* ---------- RSA: weekly ---------- */

    @Test
    public void getFullyPopulatedWeeklyMetrics_delegates() throws Exception {
        assertSame(stub, MetricsFlowExample.getFullyPopulatedWeeklyMetrics());
        verify(metricsService).getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithLowTransactionVolumeWeeklyMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithLowTransactionVolumeWeeklyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithLessThan52WeeksMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithLessThan52WeeksMetrics();
        verify(metricsService).getMetrics(UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- RSA: monthly ---------- */

    @Test
    public void getFullyPopulateMonthlyMetrics_delegates() throws Exception {
        MetricsFlowExample.getFullyPopulateMonthlyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithLowTransactionVolumeMonthlyMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithLowTransactionVolumeMonthlyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithLessThan12MonthsMonthlyMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithLessThan12MonthsMonthlyMetrics();
        verify(metricsService).getMetrics(UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- RSA: negative ---------- */

    @Test
    public void throwsMetricsNotFound_delegates() throws Exception {
        MetricsFlowExample.throwsMetricsNotFound();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsLocationNotFoundForWeekly_delegates() throws Exception {
        MetricsFlowExample.throwsLocationNotFoundForWeekly();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsLocationNotFoundForMonthly_delegates() throws Exception {
        MetricsFlowExample.throwsLocationNotFoundForMonthly();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsWeeklyConsentNotProvided_delegates() throws Exception {
        MetricsFlowExample.throwsWeeklyConsentNotProvided();
        verify(metricsService).getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID),
                false, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsMonthlyConsentNotProvided_delegates() throws Exception {
        MetricsFlowExample.throwsMonthlyConsentNotProvided();
        verify(metricsService).getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID),
                false, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsMetricFrequencyNotFound_delegates() throws Exception {
        MetricsFlowExample.throwsMetricFrequencyNotFound();
        verify(metricsService).getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true, null, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- Benchmarks ---------- */

    @Test
    public void getFullyPopulatedBenchmarksMetrics_delegates() throws Exception {
        MetricsFlowExample.getFullyPopulatedBenchmarksMetrics();
        verify(metricsService).getMetrics(UUID.fromString(FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID),
                true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    @Test
    public void getMerchantWithLowTransactionVolumeBenchmarksMetrics_delegates() throws Exception {
        MetricsFlowExample.getMerchantWithLowTransactionVolumeBenchmarksMetrics();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID),
                true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsBenchmarksMetricsNotFound_delegates() throws Exception {
        MetricsFlowExample.throwsBenchmarksMetricsNotFound();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID),
                true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsBenchmarksMetricsConsentNotProvided_delegates() throws Exception {
        MetricsFlowExample.throwsBenchmarksMetricsConsentNotProvided();
        verify(metricsService).getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID),
                false, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    @Test
    public void throwsBenchmarksMetricsLocationNotFound_delegates() throws Exception {
        MetricsFlowExample.throwsBenchmarksMetricsLocationNotFound();
        verify(metricsService).getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID),
                true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    @Test
    public void serviceMethod_returnsInjectedSingleton() throws Exception {
        assertSame(metricsService, MetricsFlowExample.service());
    }
}
