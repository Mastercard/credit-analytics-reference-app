package com.mastercard.creditanalytics.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for {@link OAuthUtils}. The {@code buildOAuth1Interceptor} happy path requires a real
 * PKCS#12 file, so we only assert error paths that don't need network/keystore I/O.
 */
public class OAuthUtilsTest {

    @Test
    public void privateConstructor_throwsIllegalStateException() throws Exception {
        Constructor<OAuthUtils> ctor = OAuthUtils.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        try {
            ctor.newInstance();
            fail("Expected IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
        }
    }

    @Test
    public void buildOAuth1Interceptor_invalidKeyfile_throws() {
        try {
            OAuthUtils.buildOAuth1Interceptor("nonexistent-file.p12", "alias", "pwd", "ck");
            fail("Expected exception loading missing keystore");
        } catch (Exception expected) {
            // any of the declared checked exceptions or a wrapping runtime exception is acceptable
            assertTrue(expected.getClass().getName(), true);
        }
    }
}
