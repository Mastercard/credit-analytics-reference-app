package com.mastercard.creditanalytics.api;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MatchingApi;
import org.openapitools.client.api.MetricsApi;
import org.openapitools.client.model.Match;
import org.openapitools.client.model.MetricsPerLocation;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.UUID;

public class ApiExamples {
    private ApiExamples() {
        throw new IllegalStateException("ApiExamples is a utility class and should not be instantiated");
    }

    /* Constants */

    public static final String SINGLE_MATCH_COMPANY_NAME = "Artisan Emporium";
    public static final String MULTIPLE_MATCHES_COMPANY_NAME = "Creative Collective";
    public static final String NO_MATCH_COMPANY_NAME = "Handcrafted Haven";

    public static final String SINGLE_MATCH_STREET_ADDRESS = "2000 Purchase St";
    public static final String MULTIPLE_MATCHES_STREET_ADDRESS = "2001 Purchase St";
    public static final String NO_MATCH_STREET_ADDRESS = "2002 Purchase St";
    public static final String EXAMPLE_POSTAL_CODE = "10577";
    public static final String EXAMPLE_CITY = "Purchase";
    public static final String EXAMPLE_STATE_PROVINCE_CODE = "NY";
    public static final String USA_COUNTRY_CODE = "USA";

    public static final String FULLY_POPULATED_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000001";
    public static final String MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000002";
    public static final String MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000003";
    public static final String NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000004";
    public static final String MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000005";
    public static final String MERCHANT_NOT_FOUND_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000006";
    public static final String CONSENT_NOT_PROVIDED_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000007";
    public static final String FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000001";
    public static final String MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000002";
    public static final String MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000005";
    public static final String MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000006";
    public static final String CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000007";

    public static final String RSA_METRICS_QUERY_PARAM = "retail_sales_analytics";
    public static final String BENCHMARKS_METRICS_QUERY_PARAM = "retail_sales_benchmarks";

    /* ApiClient Configuration */

    private static org.openapitools.client.ApiClient ApiClient;

    public static ApiClient getApiClient() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        if (ApiClient == null) {
            ApiClient = ApiHelper.getApiClient(
                "https://sandbox.api.mastercard.com/small-business/credit-analytics/locations",
                "path\\to\\your\\private_key.p12", // a reasonable place to store this would be src/main/resources/*.p12
                "your keyalias",
                "your keystorepassword",
                "consumer key from your Developer Zone project");
        }

        return ApiClient;
    }

    /*
     * Matches Use Cases
     *
     * Note: We are not exercising the standard validation logic as specified in the OpenAPI specification for this
     * endpoint - i.e. required parameters not being provided, following the expected min and max length of the various
     * string parameters and enforcing regex for allowed characters
     * */

    public static List<Match> getSingleMatch() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> getMultipleMatches() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                MULTIPLE_MATCHES_COMPANY_NAME,
                MULTIPLE_MATCHES_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsNoMatchFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                NO_MATCH_COMPANY_NAME,
                NO_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsInvalidPostalCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                "105776", // For USA, this must be ##### or #####-####
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsGetInvalidStateProvinceCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                "NA", // For USA, this must be one of the 50 valid 2-character state codes
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsInvalidCountryCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                "AUS" // this must be USA_COUNTRY_CODE for the current release of the API
        );
    }

    /*
     * Metrics Use Cases
     *
     * Note: We are not exercising the standard validation logic as specified in the OpenAPI specification for this
     * endpoint - i.e. that locationId fits the standard format for a UUID and that hasConsent is a standard boolean.
     * Using the OpenAPI-generated code we can't violate that validation logic easily, but on a raw request this
     * validation logic will trigger.
     * */

    public static MetricsPerLocation getFullyPopulatedMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getMerchantWithNoDataFromCurrentOrPreviousYearYoyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getMerchantWithLessThan52WeeksMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsMetricsNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsLocationNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID),
                false,
                RSA_METRICS_QUERY_PARAM
        );
    }

    /* Full business flow use case: Matches + Metrics */

    public static MetricsPerLocation getMetricsUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatch().get(0).getLocationId(),
                true,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getFullyPopulatedBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsConsentNotProvided() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID),
                false,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsLocationNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getBenchmarksMetricsUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatch().get(0).getLocationId(),
                true,
                BENCHMARKS_METRICS_QUERY_PARAM
        );
    }
}
