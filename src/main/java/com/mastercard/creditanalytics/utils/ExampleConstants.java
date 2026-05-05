package com.mastercard.creditanalytics.utils;

/**
 * Constants used across the example flow classes (companies, addresses, location IDs, query params).
 * Extracted verbatim from the original {@code ApiExamples} god-class.
 */
public final class ExampleConstants {
    private ExampleConstants() {
        throw new IllegalStateException("ExampleConstants is a utility class and should not be instantiated");
    }

    /* Company / address fixtures */
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

    /* Merchant identifier fixtures */
    public static final String EXAMPLE_ID_TYPE_MERCHANT_ID = "MERCHANT_ID";
    public static final String SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE = "106241230D01";
    public static final String MULTIPLE_MATCHES_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE = "106241230D02";
    public static final String EXAMPLE_INVALID_ID_TYPE = "MERCHANT_ID2";
    public static final String EXAMPLE_INVALID_ID_VALUE = "MERCHANT_ID1234567";
    public static final String EXAMPLE_AGG_MERCHANT_ID_VALUE = "106241230AGG";
    public static final String NO_MATCH_ID_VALUE = "106241230D0122";
    public static final String AGG_MERCHANT_COMPANY_EXAMPLE = "Whole Trade Inc";

    /* Location ID fixtures (Metrics) */
    public static final String FULLY_POPULATED_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000001";
    public static final String MERCHANT_WITH_LOW_TRANSACTION_VOLUME_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000002";
    public static final String MERCHANT_WITH_NO_DATA_FROM_CURRENT_OR_PREVIOUS_YEAR_YOY_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000003";
    public static final String NEW_MERCHANT_WITH_LESS_THAN_52_WEEKS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000004";
    public static final String MERCHANT_TOO_NEW_TO_HAVE_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000005";
    public static final String MERCHANT_NOT_FOUND_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000006";
    public static final String CONSENT_NOT_PROVIDED_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000007";

    /* Location ID fixtures (Benchmarks) */
    public static final String FULLY_POPULATED_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000001";
    public static final String MERCHANT_WITH_LOW_TRANSACTION_VOLUME_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000002";
    public static final String MERCHANT_TOO_NEW_TO_HAVE_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000005";
    public static final String MERCHANT_NOT_FOUND_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000006";
    public static final String CONSENT_NOT_PROVIDED_BENCHMARKS_METRICS_LOCATION_ID = "a1b2c3d4-0000-1234-abcd-000000000007";

    /* Query params */
    public static final String RSA_METRICS_QUERY_PARAM = "retail_sales_analytics";
    public static final String BENCHMARKS_METRICS_QUERY_PARAM = "retail_sales_benchmarks";
    public static final String MONTHLY = "Monthly";
    public static final String WEEKLY = "Weekly";
}
