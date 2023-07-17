/*
 * Small Business Credit Analytics API
 * A collection of Mastercard-provided APIs for retrieving merchant metrics that help in making lending decisions.
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: apisupport@mastercard.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.mastercard.creditanalytics.api;

import org.junit.Assert;
import org.junit.Test;
import org.openapitools.client.ApiException;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * API tests for combining MatchesApi and MetricsApi
 */
public class MatchesAndMetricsApiTest {
    @Test
    public void getMetricsFromMatchesTest() throws ApiException, UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        Assert.assertNotNull(ApiExamples.getMetricsUsingMatchResults());
    }
    
}
