package com.mastercard.creditanalytics.api;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class ApiHelper {
    private ApiHelper() {
        throw new IllegalStateException("ApiHelper is a utility class and should not be instantiated");
    }

    public static ApiClient getApiClient(String apiBasePath, String signingKeyPath, String signingKeyAlias, String signingKeyPassword, String consumerKey) throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        ApiClient client = new ApiClient();
        OkHttpClient.Builder httpClientBuilder = client.getHttpClient().newBuilder();

        // Configure the Mastercard service URL
        client.setBasePath(apiBasePath);

        // Load the signing key
        PrivateKey signingKey = AuthenticationUtils.loadSigningKey(signingKeyPath, signingKeyAlias, signingKeyPassword);

        // Add the interceptor code responsible for signing HTTP requests
        httpClientBuilder.addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey));
        client.setHttpClient(httpClientBuilder.build());

        return client;
    }
}
