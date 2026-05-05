package com.mastercard.creditanalytics.services;

import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MatchingApi;
import org.openapitools.client.model.Match;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MatchingService} — verifies the wrapper passes arguments through unchanged.
 */
public class MatchingServiceTest {

    private MatchingApi matchingApi;
    private MatchingService service;

    @Before
    public void setUp() {
        matchingApi = mock(MatchingApi.class);
        service = new MatchingService(matchingApi);
    }

    @Test
    public void getMatchesDelegatesToApiAndReturnsResult() throws ApiException {
        List<Match> expected = Collections.singletonList(new Match());
        when(matchingApi.getMatches("co", "addr", "10577", "Purchase", "NY", "USA", "MERCHANT_ID", "MID-1"))
                .thenReturn(expected);

        List<Match> actual = service.getMatches("co", "addr", "10577", "Purchase", "NY", "USA", "MERCHANT_ID", "MID-1");

        assertSame(expected, actual);
        verify(matchingApi).getMatches("co", "addr", "10577", "Purchase", "NY", "USA", "MERCHANT_ID", "MID-1");
    }

    @Test
    public void apiClientConstructor_buildsService() {
        // covers MatchingService(ApiClient) constructor branch
        MatchingService s = new MatchingService(new org.openapitools.client.ApiClient());
        org.junit.Assert.assertNotNull(s);
    }
}
