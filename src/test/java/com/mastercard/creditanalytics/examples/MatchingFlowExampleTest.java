package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.services.MatchingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Match;

import java.util.Collections;
import java.util.List;

import static com.mastercard.creditanalytics.utils.ExampleConstants.*;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for every static flow in {@link MatchingFlowExample}, using a mock
 * {@link MatchingService} so the live SBCA service is never contacted.
 */
public class MatchingFlowExampleTest {

    private MatchingService matchingService;
    private final List<Match> stub = Collections.singletonList(new Match());

    @Before
    public void setUp() throws Exception {
        matchingService = mock(MatchingService.class);
        when(matchingService.getMatches(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(stub);
        MatchingFlowExample.setService(matchingService);
    }

    @After
    public void tearDown() {
        MatchingFlowExample.setService(null);
    }

    @Test
    public void getSingleMatchByMID_delegates() throws Exception {
        assertSame(stub, MatchingFlowExample.getSingleMatchByMID());
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    @Test
    public void getMultipleMatchesByMID_delegates() throws Exception {
        MatchingFlowExample.getMultipleMatchesByMID();
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, MULTIPLE_MATCHES_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    @Test
    public void getSingleMatchByNameAndAddress_delegates() throws Exception {
        MatchingFlowExample.getSingleMatchByNameAndAddress();
        verify(matchingService).getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE, EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void getMultipleMatchesByNameAndAddress_delegates() throws Exception {
        MatchingFlowExample.getMultipleMatchesByNameAndAddress();
        verify(matchingService).getMatches(MULTIPLE_MATCHES_COMPANY_NAME, MULTIPLE_MATCHES_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE, EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void throwsInvalidIDType_propagatesApiException() throws Exception {
        when(matchingService.getMatches(any(), any(), any(), any(), any(),
                eq(USA_COUNTRY_CODE), eq(EXAMPLE_INVALID_ID_TYPE), eq(SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE)))
                .thenThrow(new ApiException("invalid id type"));
        try { MatchingFlowExample.throwsInvalidIDType(); } catch (ApiException ignored) { /* expected */ }
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_INVALID_ID_TYPE, SINGLE_MATCH_EXAMPLE_ID_VALUE_MERCHANT_ID_VALUE);
    }

    @Test
    public void throwsAggregatedMerchantNotPermittedByMID_delegates() throws Exception {
        MatchingFlowExample.throwsAggregatedMerchantNotPermittedByMID();
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, EXAMPLE_AGG_MERCHANT_ID_VALUE);
    }

    @Test
    public void throwsInvalidIDValue_delegates() throws Exception {
        MatchingFlowExample.throwsInvalidIDValue();
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_INVALID_ID_TYPE, EXAMPLE_INVALID_ID_VALUE);
    }

    @Test
    public void throwsNoMatchFoundByMID_delegates() throws Exception {
        MatchingFlowExample.throwsNoMatchFoundByMID();
        verify(matchingService).getMatches(null, null, null, null, null,
                USA_COUNTRY_CODE, EXAMPLE_ID_TYPE_MERCHANT_ID, NO_MATCH_ID_VALUE);
    }

    @Test
    public void throwsNoMatchFoundByNameAndAddress_delegates() throws Exception {
        MatchingFlowExample.throwsNoMatchFoundByNameAndAddress();
        verify(matchingService).getMatches(NO_MATCH_COMPANY_NAME, NO_MATCH_STREET_ADDRESS, EXAMPLE_POSTAL_CODE,
                EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void throwsAggregatedMerchantNotPermittedByNameAndAddress_delegates() throws Exception {
        MatchingFlowExample.throwsAggregatedMerchantNotPermittedByNameAndAddress();
        verify(matchingService).getMatches(AGG_MERCHANT_COMPANY_EXAMPLE, SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE, EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE,
                USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void throwsInvalidPostalCodeApiException_delegates() throws Exception {
        MatchingFlowExample.throwsInvalidPostalCodeApiException();
        verify(matchingService).getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS,
                "105776", EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void throwsGetInvalidStateProvinceCodeApiException_delegates() throws Exception {
        MatchingFlowExample.throwsGetInvalidStateProvinceCodeApiException();
        verify(matchingService).getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE, EXAMPLE_CITY, "NA", USA_COUNTRY_CODE, null, null);
    }

    @Test
    public void throwsInvalidCountryCodeApiException_delegates() throws Exception {
        MatchingFlowExample.throwsInvalidCountryCodeApiException();
        verify(matchingService).getMatches(SINGLE_MATCH_COMPANY_NAME, SINGLE_MATCH_STREET_ADDRESS,
                EXAMPLE_POSTAL_CODE, EXAMPLE_CITY, EXAMPLE_STATE_PROVINCE_CODE, "AUS", null, null);
    }

    @Test
    public void serviceMethod_returnsInjectedSingleton() throws Exception {
        assertSame(matchingService, MatchingFlowExample.service());
        MatchingFlowExample.getSingleMatchByMID();
        verify(matchingService, times(1)).getMatches(any(), any(), any(), any(), any(), any(), any(), any());
    }
}
