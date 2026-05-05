package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.configuration.ApiClientConfiguration;
import com.mastercard.creditanalytics.services.MatchingService;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Match;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import static com.mastercard.creditanalytics.utils.ExampleConstants.*;

/**
 * End-to-end usage examples for the {@code /matches} endpoint
 * (single / multiple match, by-MID, by-name+address, plus negative cases).
 *
 * <p>Methods are intentionally static to mirror the legacy {@code ApiExamples} call surface.</p>
 */
public final class MatchingFlowExample {

    private static MatchingService cachedService;

    private MatchingFlowExample() {
        throw new IllegalStateException("MatchingFlowExample is a utility class and should not be instantiated");
    }

    static MatchingService service()
            throws UnrecoverableKeyException, CertificateException, IOException,
                   KeyStoreException, NoSuchAlgorithmException {
        if (cachedService == null) {
            cachedService = new MatchingService(ApiClientConfiguration.getApiClient());
        }
        return cachedService;
    }

    /* ---------- happy path: by MID ---------- */

    public static List<Match> getSingleMatchByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    public static List<Match> getMultipleMatchesByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, MULTIPLE_MATCHES_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    /* ---------- happy path: by name + address ---------- */

    public static List<Match> getSingleMatchByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    public static List<Match> getMultipleMatchesByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(MULTIPLE_MATCHES_COMPANY_NAME, MULTIPLE_MATCHES_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    /* ---------- negative cases: by MID ---------- */

    public static List<Match> throwsInvalidIDType() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_INVALID_ID_TYPE, SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    public static List<Match> throwsAggregatedMerchantNotPermittedByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, EXAMPLE_AGG_MERCHANT_ID_VALUE);
    }

    public static List<Match> throwsInvalidIDValue() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_INVALID_ID_TYPE, EXAMPLE_INVALID_ID_VALUE);
    }

    public static List<Match> throwsNoMatchFoundByMID() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, NO_MATCH_ID_VALUE);
    }

    /* ---------- negative cases: by name + address ---------- */

    public static List<Match> throwsNoMatchFoundByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(NO_MATCH_COMPANY_NAME, NO_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    public static List<Match> throwsAggregatedMerchantNotPermittedByNameAndAddress() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(AGG_MERCHANT_COMPANY_EXAMPLE, SINGLE_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    public static List<Match> throwsInvalidPostalCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS,
                "105776", // For USA, this must be ##### or #####-####
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    public static List<Match> throwsGetInvalidStateProvinceCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY,
                "NA", // For USA, this must be one of the 50 valid 2-character state codes
                USA_COUNTRY_CODE, null, null);
    }

    public static List<Match> throwsInvalidCountryCodeApiException() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        return service().getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE,
                "AUS", // must be one of the ISO standard country codes supported by SBCA
                null, null);
    }
}
