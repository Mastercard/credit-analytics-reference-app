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

    public static final String SINGLE_MATCH_COMPANY_NAME = "SingleMatchCompany";
    public static final String MULTIPLE_MATCHES_COMPANY_NAME = "MultipleMatchesCompany";
    public static final String NO_MATCH_COMPANY_NAME = "NoMatchCompany";

    public static final String EXAMPLE_STREET_ADDRESS = "2000 Purchase St";
    public static final String EXAMPLE_POSTAL_CODE = "10577";
    public static final String EXAMPLE_CITY = "Purchase";
    public static final String EXAMPLE_STATE_PROVINCE_CODE = "NY";
    public static final String USA_COUNTRY_CODE = "USA";

    public static final String FULLY_POPULATED_METRICS_LOCATION_ID = "00000000-0000-0000-0000-000000000001";
    public static final String PARTIALLY_POPULATED_METRICS_LOCATION_ID = "00000000-0000-0000-0000-000000000002";
    public static final String PARTIAL_YEAR_METRICS_LOCATION_ID = "00000000-0000-0000-0000-000000000003";
    public static final String NEWLY_ACCEPTING_LOCATION_ID = "00000000-0000-0000-0000-000000000004";
    public static final String NOT_FOUND_LOCATION_ID = "00000000-0000-0000-0000-000000000005";

    /* ApiClient Configuration */

    private static org.openapitools.client.ApiClient ApiClient;

    public static ApiClient getApiClient() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        if (ApiClient == null){
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
                EXAMPLE_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> getMultipleMatches() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                MULTIPLE_MATCHES_COMPANY_NAME,
                EXAMPLE_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsNoMatchFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                NO_MATCH_COMPANY_NAME,
                EXAMPLE_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsInvalidPostalCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                EXAMPLE_STREET_ADDRESS,
                "105776", // For USA, this must be ##### or #####-####
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsGetInvalidStateProvinceCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                EXAMPLE_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                "NA", // For USA, this must be one of the 50 valid 2-character state codes
                USA_COUNTRY_CODE
        );
    }

    public static List<Match> throwsInvalidCountryCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                EXAMPLE_STREET_ADDRESS,
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
                true
        );
    }

    public static MetricsPerLocation getPartiallyPopulatedMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(PARTIALLY_POPULATED_METRICS_LOCATION_ID),
                true
        );
    }

    public static MetricsPerLocation getPartialYearMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(PARTIAL_YEAR_METRICS_LOCATION_ID),
                true
        );
    }

    public static MetricsPerLocation throwsMetricsNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(NEWLY_ACCEPTING_LOCATION_ID),
                true
        );
    }

    public static MetricsPerLocation throwsLocationNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(NOT_FOUND_LOCATION_ID),
                true
        );
    }

    public static MetricsPerLocation throwsConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                false
        );
    }

    /* Full business flow use case: Matches + Metrics */

    public static MetricsPerLocation getMetricsUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatch().get(0).getLocationId(),
                true
        );
    }
}
