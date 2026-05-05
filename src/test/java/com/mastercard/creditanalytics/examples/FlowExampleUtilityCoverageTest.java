package com.mastercard.creditanalytics.examples;

import com.mastercard.creditanalytics.configuration.ApiClientConfiguration;
import org.junit.After;
import org.junit.Test;
import org.openapitools.client.ApiClient;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Covers the private constructors of the three flow-example utility classes (defensive guards),
 * plus the lazy {@code service()} init branch via reflection on {@link ApiClientConfiguration}.
 */
public class FlowExampleUtilityCoverageTest {

    @After
    public void resetCaches() throws Exception {
        MatchingFlowExample.setService(null);
        MetricsFlowExample.setService(null);
        Field f = ApiClientConfiguration.class.getDeclaredField("cachedApiClient");
        f.setAccessible(true);
        f.set(null, null);
    }

    @Test
    public void matchingFlowPrivateConstructor_throws() throws Exception {
        assertPrivateCtorThrows(MatchingFlowExample.class);
    }

    @Test
    public void metricsFlowPrivateConstructor_throws() throws Exception {
        assertPrivateCtorThrows(MetricsFlowExample.class);
    }

    @Test
    public void matchesAndMetricsFlowPrivateConstructor_throws() throws Exception {
        assertPrivateCtorThrows(MatchesAndMetricsFlowExample.class);
    }

    @Test
    public void matchingFlow_lazyServiceInitUsesCachedApiClient() throws Exception {
        // pre-seed ApiClientConfiguration cache to bypass keystore I/O
        Field f = ApiClientConfiguration.class.getDeclaredField("cachedApiClient");
        f.setAccessible(true);
        f.set(null, new ApiClient());
        MatchingFlowExample.setService(null);
        assertNotNull(MatchingFlowExample.service());
    }

    @Test
    public void metricsFlow_lazyServiceInitUsesCachedApiClient() throws Exception {
        Field f = ApiClientConfiguration.class.getDeclaredField("cachedApiClient");
        f.setAccessible(true);
        f.set(null, new ApiClient());
        MetricsFlowExample.setService(null);
        assertNotNull(MetricsFlowExample.service());
    }

    private static <T> void assertPrivateCtorThrows(Class<T> cls) throws Exception {
        Constructor<T> ctor = cls.getDeclaredConstructor();
        ctor.setAccessible(true);
        try {
            ctor.newInstance();
            fail("Expected IllegalStateException from " + cls.getSimpleName());
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }
}
