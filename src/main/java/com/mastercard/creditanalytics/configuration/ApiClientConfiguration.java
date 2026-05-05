package com.mastercard.creditanalytics.configuration;

import com.mastercard.creditanalytics.utils.OAuthUtils;
import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;

/**
 * Builds and caches the generated {@link ApiClient}, attaching the Mastercard OAuth 1.0a
 * signing interceptor. Replaces the legacy {@code ApiHelper} / {@code ApiExamples.getApiClient()}.
 *
 * <p>Configuration is read from {@code application.properties} on the classpath.</p>
 */
public final class ApiClientConfiguration {

    private static final String PROPERTIES_FILE = "application.properties";

    private static final String PROP_BASE_PATH = "creditanalytics.api.basePath";
    private static final String PROP_PKCS12 = "mastercard.oauth.pkcs12KeyFile";
    private static final String PROP_KEY_ALIAS = "mastercard.oauth.keyAlias";
    private static final String PROP_KEY_PASSWORD = "mastercard.oauth.keyPassword";
    private static final String PROP_CONSUMER_KEY = "mastercard.oauth.consumerKey";

    private static ApiClient cachedApiClient;

    private ApiClientConfiguration() {
        throw new IllegalStateException("ApiClientConfiguration is a utility class and should not be instantiated");
    }

    /**
     * Returns the singleton {@link ApiClient}, building it on first call.
     * Preserves the lazy single-thread initialization semantics of the legacy code.
     */
    public static ApiClient getApiClient()
            throws UnrecoverableKeyException, CertificateException, IOException,
                   KeyStoreException, NoSuchAlgorithmException {
        if (cachedApiClient == null) {
            Properties props = loadProperties();
            cachedApiClient = buildApiClient(
                    props.getProperty(PROP_BASE_PATH),
                    props.getProperty(PROP_PKCS12),
                    props.getProperty(PROP_KEY_ALIAS),
                    props.getProperty(PROP_KEY_PASSWORD),
                    props.getProperty(PROP_CONSUMER_KEY)
            );
        }
        return cachedApiClient;
    }

    static ApiClient buildApiClient(String basePath,
                                    String pkcs12KeyFile,
                                    String keyAlias,
                                    String keyPassword,
                                    String consumerKey)
            throws UnrecoverableKeyException, CertificateException, IOException,
                   KeyStoreException, NoSuchAlgorithmException {
        ApiClient client = new ApiClient();
        client.setBasePath(basePath);

        OkHttpClient.Builder httpClientBuilder = client.getHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(
                OAuthUtils.buildOAuth1Interceptor(pkcs12KeyFile, keyAlias, keyPassword, consumerKey));
        client.setHttpClient(httpClientBuilder.build());
        return client;
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream in = cl.getResourceAsStream(PROPERTIES_FILE)) {
            if (in == null) {
                throw new IllegalStateException("Could not find " + PROPERTIES_FILE + " on the classpath");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read " + PROPERTIES_FILE, e);
        }
        return properties;
    }
}
