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
 * Integration tests for {@link MatchingFlowExample} — calls the live SBCA service.
 */
public class MatchingFlowExampleTest {

    @Test
    public void getMatchesTest() throws ApiException, UnrecoverableKeyException, CertificateException,
            IOException, KeyStoreException, NoSuchAlgorithmException {
        Assert.assertNotNull(MatchingFlowExample.getSingleMatchByNameAndAddress());
        Assert.assertNotNull(MatchingFlowExample.getMultipleMatchesByNameAndAddress());
        Assert.assertNotNull(MatchingFlowExample.getSingleMatchByMID());
        Assert.assertNotNull(MatchingFlowExample.getMultipleMatchesByMID());

        // negative cases
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsNoMatchFoundByNameAndAddress);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsInvalidPostalCodeApiException);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsGetInvalidStateProvinceCodeApiException);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsInvalidCountryCodeApiException);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsNoMatchFoundByMID);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsInvalidIDType);
        Assert.assertThrows(ApiException.class, MatchingFlowExample::throwsInvalidIDValue);
    }
}
