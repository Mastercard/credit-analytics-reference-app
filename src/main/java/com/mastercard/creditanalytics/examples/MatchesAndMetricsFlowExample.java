package com.mastercard.creditanalytics.examples;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.MetricsPerLocation;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import static com.mastercard.creditanalytics.utils.ExampleConstants.BENCHMARKS_METRICS_QUERY_PARAM;
import static com.mastercard.creditanalytics.utils.ExampleConstants.MONTHLY;
import static com.mastercard.creditanalytics.utils.ExampleConstants.RSA_METRICS_QUERY_PARAM;
import static com.mastercard.creditanalytics.utils.ExampleConstants.WEEKLY;

/**
 * Combined match-then-metrics business flow:
 * resolve a merchant by name + address, then pull metrics for the first matched location.
 */
public final class MatchesAndMetricsFlowExample {

    private MatchesAndMetricsFlowExample() {
        throw new IllegalStateException("MatchesAndMetricsFlowExample is a utility class and should not be instantiated");
    }

    public static MetricsPerLocation getMetricsWeeklyUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return MetricsFlowExample.service().getMetrics(
                MatchingFlowExample.getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMetricsMonthlyUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return MetricsFlowExample.service().getMetrics(
                MatchingFlowExample.getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getBenchmarksMetricsUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return MetricsFlowExample.service().getMetrics(
                MatchingFlowExample.getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }
}
