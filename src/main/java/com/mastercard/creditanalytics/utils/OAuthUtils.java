package com.mastercard.creditanalytics.utils;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import okhttp3.Interceptor;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Helper for building the Mastercard OAuth 1.0a request-signing interceptor.
 * Logic extracted verbatim from the original {@code ApiHelper}.
 */
public final class OAuthUtils {
    private OAuthUtils() {
        throw new IllegalStateException("OAuthUtils is a utility class and should not be instantiated");
    }

    /**
     * Loads the PKCS#12 signing key and returns an OkHttp interceptor that signs requests with OAuth 1.0a.
     */
    public static Interceptor buildOAuth1Interceptor(String pkcs12KeyFile,
                                                     String keyAlias,
                                                     String keyPassword,
                                                     String consumerKey)
            throws UnrecoverableKeyException, CertificateException, IOException,
                   KeyStoreException, NoSuchAlgorithmException {
        PrivateKey signingKey = AuthenticationUtils.loadSigningKey(pkcs12KeyFile, keyAlias, keyPassword);
        return new OkHttpOAuth1Interceptor(consumerKey, signingKey);
    }
}
