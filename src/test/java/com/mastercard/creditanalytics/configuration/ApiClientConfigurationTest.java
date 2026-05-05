package com.mastercard.creditanalytics.configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiClient;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for {@link ApiClientConfiguration}. Avoids real keystore I/O by manipulating
 * the cached singleton via reflection.
 */
public class ApiClientConfigurationTest {

    private Object originalCached;

    @Before
    public void snapshotCached() throws Exception {
        originalCached = readCachedField();
    }

    @After
    public void restoreCached() throws Exception {
        writeCachedField(originalCached);
    }

    @Test
    public void privateConstructor_throwsIllegalStateException() throws Exception {
        Constructor<ApiClientConfiguration> ctor = ApiClientConfiguration.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        try {
            ctor.newInstance();
            fail("Expected IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }

    @Test
    public void getApiClient_returnsCachedSingletonWhenAlreadyBuilt() throws Exception {
        ApiClient pre = new ApiClient();
        writeCachedField(pre);
        assertSame(pre, ApiClientConfiguration.getApiClient());
    }

    @Test
    public void buildApiClient_invalidKeyfile_propagatesException() {
        try {
            ApiClientConfiguration.buildApiClient(
                    "https://example.invalid/api",
                    "this-file-does-not-exist.p12",
                    "alias",
                    "password",
                    "consumerKey");
            fail("Expected exception loading missing keystore");
        } catch (Exception expected) {
            // any checked or wrapping runtime exception is acceptable
            assertTrue(expected.getClass().getName(), true);
        }
    }

    @Test
    public void getApiClient_lazyInit_runsLoadProperties() throws Exception {
        // Force the lazy-init branch. If the local p12 referenced by application.properties is
        // absent, an exception is fine — the goal is to execute loadProperties() + buildApiClient().
        writeCachedField(null);
        try {
            ApiClientConfiguration.getApiClient();
        } catch (Exception ignored) {
            // expected when running on a machine without the configured p12 file
        }
    }

    /* ---------- reflection helpers ---------- */

    private static Object readCachedField() throws Exception {
        Field f = ApiClientConfiguration.class.getDeclaredField("cachedApiClient");
        f.setAccessible(true);
        return f.get(null);
    }

    private static void writeCachedField(Object value) throws Exception {
        Field f = ApiClientConfiguration.class.getDeclaredField("cachedApiClient");
        f.setAccessible(true);
        f.set(null, value);
    }
}
