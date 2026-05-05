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
 * Integration tests for {@link MatchesAndMetricsFlowExample} — combined match -> metrics flow.
 */
public class MatchesAndMetricsFlowExampleTest {

    @Test
    public void getMetricsFromMatchesTest() throws ApiException, UnrecoverableKeyException, CertificateException,
            IOException, KeyStoreException, NoSuchAlgorithmException {
        Assert.assertNotNull(MatchesAndMetricsFlowExample.getMetricsMonthlyUsingMatchResults());
        Assert.assertNotNull(MatchesAndMetricsFlowExample.getMetricsWeeklyUsingMatchResults());
        Assert.assertNotNull(MatchesAndMetricsFlowExample.getBenchmarksMetricsUsingMatchResults());
    }
}
