package com.mastercard.creditanalytics.examples;

import org.junit.Assert;
import org.junit.Test;
import org.openapitools.client.ApiException;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Integration tests for {@link MetricsFlowExample} — calls the live SBCA service.
 */
public class MetricsFlowExampleTest {

    @Test
    public void getMetricsTest() throws ApiException, UnrecoverableKeyException, CertificateException,
            IOException, KeyStoreException, NoSuchAlgorithmException {
        Assert.assertNotNull(MetricsFlowExample.getFullyPopulatedWeeklyMetrics());

        Assert.assertNotNull(MetricsFlowExample.getFullyPopulateMonthlyMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithLowTransactionVolumeMonthlyMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithLowTransactionVolumeWeeklyMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithLessThan52WeeksMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithLessThan12MonthsMonthlyMetrics());

        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsMetricsNotFound);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsLocationNotFoundForMonthly);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsLocationNotFoundForWeekly);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsMonthlyConsentNotProvided);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsWeeklyConsentNotProvided);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsMetricFrequencyNotFound);

        // Benchmarks metrics test cases
        Assert.assertNotNull(MetricsFlowExample.getFullyPopulatedBenchmarksMetrics());
        Assert.assertNotNull(MetricsFlowExample.getMerchantWithLowTransactionVolumeBenchmarksMetrics());

        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsBenchmarksMetricsNotFound);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsBenchmarksMetricsLocationNotFound);
        Assert.assertThrows(ApiException.class, MetricsFlowExample::throwsBenchmarksMetricsConsentNotProvided);
    }
}
