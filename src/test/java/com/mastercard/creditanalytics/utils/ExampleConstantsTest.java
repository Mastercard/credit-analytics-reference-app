package com.mastercard.creditanalytics.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the {@link ExampleConstants} utility class — ensures values are stable
 * and the private constructor cannot be used.
 */
public class ExampleConstantsTest {

    @Test
    public void constantsHaveExpectedValues() {
        assertEquals("Artisan Emporium", ExampleConstants.SINGLE_MATCH_COMPANY_NAME);
        assertEquals("Creative Collective", ExampleConstants.MULTIPLE_MATCHES_COMPANY_NAME);
        assertEquals("Handcrafted Haven", ExampleConstants.NO_MATCH_COMPANY_NAME);
        assertEquals("USA", ExampleConstants.USA_COUNTRY_CODE);
        assertEquals("MERCHANT_ID", ExampleConstants.EXAMPLE_ID_TYPE_MERCHANT_ID);
        assertEquals("retail_sales_analytics", ExampleConstants.RSA_METRICS_QUERY_PARAM);
        assertEquals("retail_sales_benchmarks", ExampleConstants.BENCHMARKS_METRICS_QUERY_PARAM);
        assertEquals("Monthly", ExampleConstants.MONTHLY);
        assertEquals("Weekly", ExampleConstants.WEEKLY);
        assertNotNull(ExampleConstants.FULLY_POPULATED_METRICS_LOCATION_ID);
    }

    @Test
    public void privateConstructor_throwsIllegalStateException() throws Exception {
        Constructor<ExampleConstants> ctor = ExampleConstants.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        try {
            ctor.newInstance();
            fail("Expected IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }
}
