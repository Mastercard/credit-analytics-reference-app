package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.configuration.ApiClientConfiguration;
import com.mastercard.creditanalytics.services.MetricsService;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.MetricsPerLocation;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

import static com.mastercard.creditanalytics.utils.ExampleConstants.*;

/**
 * End-to-end usage examples for the {@code /metrics} endpoint
 * (RSA + benchmarks, weekly + monthly, plus negative cases).
 */
public final class MetricsFlowExample {

    private static MetricsService cachedService;

    private MetricsFlowExample() {
        throw new IllegalStateException("MetricsFlowExample is a utility class and should not be instantiated");
    }

    static MetricsService service()
            throws UnrecoverableKeyException, CertificateException, IOException,
                   KeyStoreException, NoSuchAlgorithmException {
        if (cachedService == null) {
            cachedService = new MetricsService(ApiClientConfiguration.getApiClient());
        }
        return cachedService;
    }

    /* ---------- RSA: weekly happy path ---------- */

    public static MetricsPerLocation getFullyPopulatedWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithLessThan52WeeksMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- RSA: monthly happy path ---------- */

    public static MetricsPerLocation getFullyPopulateMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID), true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID), true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID), true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithLessThan12MonthsMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID), true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- RSA: negative cases ---------- */

    public static MetricsPerLocation throwsMetricsNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsLocationNotFoundForWeekly() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID), true, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsLocationNotFoundForMonthly() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID), true, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsWeeklyConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID), false, WEEKLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsMonthlyConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID), false, MONTHLY, RSA_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsMetricFrequencyNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMetrics(UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID), true, null, RSA_METRICS_QUERY_PARAM);
    }

    /* ---------- Benchmarks ---------- */

    public static MetricsPerLocation getFullyPopulatedBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return service().getMetrics(UUID.fromString(FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID), true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID), true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsBenchmarksMetricsNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID), true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsBenchmarksMetricsConsentNotProvided() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return service().getMetrics(UUID.fromString(CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID), false, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }

    public static MetricsPerLocation throwsBenchmarksMetricsLocationNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return service().getMetrics(UUID.fromString(MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID), true, MONTHLY, BENCHMARKS_METRICS_QUERY_PARAM);
    }
}
