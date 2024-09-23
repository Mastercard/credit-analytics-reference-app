package com.mastercard.creditanalytics.main;

import com.mastercard.creditanalytics.api.ApiExamples;
import org.openapitools.client.ApiException;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditAnalyticsMain {
    public static void main(String[] args) throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, ApiException {
        Logger log = Logger.getLogger("CreditAnalyticsMain");

        // Match by MID examples
        log.log(Level.INFO,"SingleMatchByMID: {0}", ApiExamples.getSingleMatchByMID());
        log.log(Level.INFO,"MultipleMatchesByMID: {0}", ApiExamples.getMultipleMatchByMID());

        // Match by MID error examples
        try{ ApiExamples.throwsInvalidIDType(); } catch(Exception e){ log.info(e.getMessage()); }
        try{ ApiExamples.throwsNoMatchFoundByMID(); } catch (Exception e){ log.info(e.getMessage()); }

        // Matches by Name and Address examples
        log.log(Level.INFO, "SingleMatchByNameAndAddress: {0}", ApiExamples.getSingleMatchByNameAndAddress());
        log.log(Level.INFO, "MultipleMatchesByNameAndAddress: {0}", ApiExamples.getMultipleMatchesByNameAndAddress());

        // Matches by Name and Address error examples
        try { ApiExamples.throwsNoMatchFoundByNameAndAddress(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsInvalidPostalCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsGetInvalidStateProvinceCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsInvalidCountryCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }

        // Metrics examples
        log.log(Level.INFO, "FullyPopulatedMetrics: {0}", ApiExamples.getFullyPopulatedMetrics());
        log.log(Level.INFO, "LowTransactionVolumeMetrics: {0}", ApiExamples.getMerchantWithLowTransactionVolumeMetrics());
        log.log(Level.INFO, "NoDataFromCurrentOrPreviousYearYoyMetrics: {0}", ApiExamples.getMerchantWithNoDataFromCurrentOrPreviousYearYoyMetrics());
        log.log(Level.INFO, "LessThan52WeeksMetrics: {0}", ApiExamples.getMerchantWithLessThan52WeeksMetrics());

        // Metrics error examples
        try { ApiExamples.throwsMetricsNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsLocationNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsConsentNotProvided(); } catch (Exception e) { log.info(e.getMessage()); }

        // Example business use case calling matches, then metrics
        // This will return the same metrics as ApiExamples.GetFullyPopulatedMetrics()
        log.log(Level.INFO, "MetricsFromMatch: {0}", ApiExamples.getMetricsUsingMatchResults());

        //Benchmarks metrics examples
        log.log(Level.INFO, "FullyPopulatedBenchmarksMetrics: {0}", ApiExamples.getFullyPopulatedBenchmarksMetrics());
        log.log(Level.INFO, "LowTransactionVolumeBenchmarksMetrics: {0}", ApiExamples.getMerchantWithLowTransactionVolumeBenchmarksMetrics());

        //Benchmarks metrics error examples
        try { ApiExamples.throwsBenchmarksMetricsNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsBenchmarksMetricsLocationNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { ApiExamples.throwsBenchmarksMetricsConsentNotProvided(); } catch (Exception e) { log.info(e.getMessage()); }

        // Example business use case calling matches, then benchmarks metrics
        // This will return the same metrics as ApiExamples.getFullyPopulatedBenchmarksMetrics()
        log.log(Level.INFO, "MetricsFromMatch: {0}", ApiExamples.getBenchmarksMetricsUsingMatchResults());
    }
}

