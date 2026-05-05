package com.mastercard.creditanalytics.main;

import org.junit.Test;
import org.openapitools.client.ApiException;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Smoke test for {@link CreditAnalyticsMain} — runs the entry point end-to-end.
 */
public class CreditAnalyticsMainTest {

    @Test
    public void runCreditAnalyticsMainTest() throws ApiException, UnrecoverableKeyException, CertificateException,
            IOException, KeyStoreException, NoSuchAlgorithmException {
        CreditAnalyticsMain.main(new String[0]);
    }
}
