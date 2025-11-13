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
    public static final String EXAMPLE_ID_TYPE_MERCHANT_ID = "MERCHANT_ID";
    public static final String SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE = "106241230D01";
    public static final String MULTIPLE_MATCHES_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE = "106241230D02";
    public static final String EXAMPLE_INVALID_ID_TYPE = "MERCHANT_ID2";
    public static final String EXAMPLE_INVALID_ID_VALUE = "MERCHANT_ID1234567";
    public static final String EXAMPLE_AGG_ID_TYPE = "106241230AGG";
    public static final String NO_MATCH_ID_VALUE = "106241230D0122";
    public static final String NO_MATCH_COMPANY_NAME_AGG = "Whole Trade Inc";
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
    private static final String MONTHLY = "Monthly";
    private static final String WEEKLY = "Weekly";
    /* ApiClient Configuration */

    private static org.openapitools.client.ApiClient ApiClient;

    public static ApiClient getApiClient() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        if (ApiClient == null){
            ApiClient = ApiHelper.getApiClient(
                    "https://sandbox.api.mastercard.com/small-business/credit-analytics/locations",
                    "C:\\Users\\e168505\\Downloads\\SBCA-sandbox-signing 3.p12",
                    "keyalias",
                    "keyalias123",
                    "rc9ZEL38hO3HEy58NJ-mxR3d-NMV7OAtlit-uGsX1b414349!d57c19b3a3414a2eaca7582d039e63e20000000000000000");
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

    public static List<Match> getSingleMatchByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_ID_TYPE_MERCHANT_ID,
                SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE
        );
    }
    public static List<Match> getMultipleMatchesByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_ID_TYPE_MERCHANT_ID,
                MULTIPLE_MATCHES_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE
        );
    }

    public static List<Match> getSingleMatchByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE,
                null,
                null
        );
    }

    public static List<Match> getMultipleMatchesByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                MULTIPLE_MATCHES_COMPANY_NAME,
                MULTIPLE_MATCHES_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE,
                null,
                null
        );
    }

    public static List<Match> throwsInvalidIDType() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_INVALID_ID_TYPE ,
                SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE
        );
    }

    public static List<Match> throwsAggregatedMerchantNotPermittedByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_INVALID_ID_TYPE,
                EXAMPLE_AGG_ID_TYPE

        );
    }

    public static List<Match> throwsInvalidIDValue() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_INVALID_ID_TYPE ,
                EXAMPLE_INVALID_ID_VALUE
        );
    }

    public static List<Match> throwsNoMatchFoundByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                null,
                null,
                null,
                null,
                null,
                USA_COUNTRY_CODE,
                EXAMPLE_ID_TYPE_MERCHANT_ID,
                NO_MATCH_ID_VALUE
        );
    }
    public static List<Match> throwsNoMatchFoundByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                NO_MATCH_COMPANY_NAME,
                NO_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE,
                null,
                null
        );
    }
    public static List<Match> throwsAggregatedMerchantNotPermittedByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                NO_MATCH_COMPANY_NAME_AGG,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE,
                null,
                null
        );
    }

    public static List<Match> throwsInvalidPostalCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                "105776", // For USA, this must be ##### or #####-####
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE,
                null,
                null
        );
    }



    public static List<Match> throwsGetInvalidStateProvinceCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                "NA", // For USA, this must be one of the 50 valid 2-character state codes
                USA_COUNTRY_CODE,
                null,
                null
        );
    }

    public static List<Match> throwsInvalidCountryCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MatchingApi(getApiClient()).getMatches(
                SINGLE_MATCH_COMPANY_NAME,
                SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                EXAMPLE_STATE_PROVINCE_CODE,
                "AUS",// this must be one of the ISO standard country codes supported by SBCA application
                null,
                null
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

    public static MetricsPerLocation getFullyPopulatedWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation getMerchantWithLowTransactionVolumeWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM

        );
    }
    public static MetricsPerLocation getMerchantWithLowTransactionVolumeMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }
    public static MetricsPerLocation getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM
        );
    }


    public static MetricsPerLocation getMerchantWithLessThan52WeeksMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }
    public static MetricsPerLocation getMerchantWithLessThan12MonthsMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsMetricsNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsLocationNotFoundForWeekly() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }
    public static MetricsPerLocation throwsLocationNotFoundForMonthly() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_NOT_FOUND_LOCATION_ID),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation throwsWeeklyConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID),
                false,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }
    public static MetricsPerLocation throwsMonthlyConsentNotProvided() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(CONSENT_NOT_PROVIDED_LOCATION_ID),
                false,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM
        );
    }

    /* Full business flow use case: Matches + Metrics */

    public static MetricsPerLocation getMetricsWeeklyUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true,
                WEEKLY,
                RSA_METRICS_QUERY_PARAM
        );
    }
    public static MetricsPerLocation getMetricsMonthlyUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM
        );
    }

    public static MetricsPerLocation getFullyPopulatedBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }
    public static MetricsPerLocation getMerchantWithLowTransactionVolumeBenchmarksMetrics() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsConsentNotProvided() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID),
                false,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation throwsBenchmarksMetricsLocationNotFound() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID),
                true,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }

    public static MetricsPerLocation getBenchmarksMetricsUsingMatchResults() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                getSingleMatchByNameAndAddress().get(0).getLocationId(),
                true,
                MONTHLY,
                BENCHMARKS_METRICS_QUERY_PARAM

        );
    }
    public static MetricsPerLocation getFullyPopulateMonthlyMetrics() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true,
                MONTHLY,
                RSA_METRICS_QUERY_PARAM

        );
    }
    public static MetricsPerLocation throwsMetricFrequencyNotFound() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return new MetricsApi(getApiClient()).getMetrics(
                UUID.fromString(FULLY_POPULATED_METRICS_LOCATION_ID),
                true,
                null,
                RSA_METRICS_QUERY_PARAM
        );
    }
}
