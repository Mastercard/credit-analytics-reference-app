package com.mastercard.creditanalytics.main;

import com.mastercard.creditanalytics.examples.MatchesAndMetricsFlowExample;
import com.mastercard.creditanalytics.examples.MatchingFlowExample;
import com.mastercard.creditanalytics.examples.MetricsFlowExample;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Match;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application entry point. Mirrors petstore's {@code PetstoreApplication} role
 * (kept under the legacy FQN so the executable-jar manifest continues to work).
 */
public class CreditAnalyticsMain {

    public static void main(String[] args) throws UnrecoverableKeyException, CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, ApiException {
        Logger log = Logger.getLogger("CreditAnalyticsMain");

        // establishing matches and metrics flow
        List<Match> matchList = MatchingFlowExample.getSingleMatchByMID();
        log.log(Level.INFO, "MatchList: {0}", matchList);
        log.log(Level.INFO, "MetricsFromMatch for Monthly : {0}", MatchesAndMetricsFlowExample.getMetricsMonthlyUsingMatchResults());
        log.log(Level.INFO, "MetricsFromMatch for Weekly: {0}", MatchesAndMetricsFlowExample.getMetricsWeeklyUsingMatchResults());

        // Match by MID examples
        log.log(Level.INFO, "SingleMatchByMID: {0}", MatchingFlowExample.getSingleMatchByMID());
        log.log(Level.INFO, "MultipleMatchesByMID: {0}", MatchingFlowExample.getMultipleMatchesByMID());

        // Match by MID error examples
        try { MatchingFlowExample.throwsInvalidIDType(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsNoMatchFoundByMID(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsAggregatedMerchantNotPermittedByMID(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsInvalidIDValue(); } catch (Exception e) { log.info(e.getMessage()); }

        // Matches by Name and Address examples
        log.log(Level.INFO, "SingleMatchByNameAndAddress: {0}", MatchingFlowExample.getSingleMatchByNameAndAddress());
        log.log(Level.INFO, "MultipleMatchesByNameAndAddress: {0}", MatchingFlowExample.getMultipleMatchesByNameAndAddress());

        // Matches by Name and Address error examples
        try { MatchingFlowExample.throwsNoMatchFoundByNameAndAddress(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsInvalidPostalCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsAggregatedMerchantNotPermittedByNameAndAddress(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsGetInvalidStateProvinceCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MatchingFlowExample.throwsInvalidCountryCodeApiException(); } catch (Exception e) { log.info(e.getMessage()); }

        // Metrics examples (weekly)
        log.log(Level.INFO, "FullyPopulatedWeeklyMetrics: {0}", MetricsFlowExample.getFullyPopulatedWeeklyMetrics());
        log.log(Level.INFO, "LowTransactionVolumeWeeklyMetrics: {0}", MetricsFlowExample.getMerchantWithLowTransactionVolumeWeeklyMetrics());
        log.log(Level.INFO, "NoDataFromCurrentOrPreviousYearYoyWeeklyMetrics: {0}", MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyWeeklyMetrics());
        log.log(Level.INFO, "LessThan52WeeksWeeklyMetrics: {0}", MetricsFlowExample.getMerchantWithLessThan52WeeksMetrics());

        // Metrics examples (monthly)
        log.log(Level.INFO, "LowTransactionVolumeMonthlyMetrics: {0}", MetricsFlowExample.getMerchantWithLowTransactionVolumeMonthlyMetrics());
        log.log(Level.INFO, "NoDataFromCurrentOrPreviousYearYoyMonthlyMetrics: {0}", MetricsFlowExample.getMerchantWithNoDataFromCurrentOrPreviousYearYoyMonthlyMetrics());
        log.log(Level.INFO, "LessThan12MonthsMonthlyMetrics: {0}", MetricsFlowExample.getMerchantWithLessThan12MonthsMonthlyMetrics());
        log.log(Level.INFO, "FullyPopulatedMetrics for monthly: {0}", MetricsFlowExample.getFullyPopulateMonthlyMetrics());

        // Metrics error examples
        try { MetricsFlowExample.throwsMetricsNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsLocationNotFoundForWeekly(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsLocationNotFoundForMonthly(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsWeeklyConsentNotProvided(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsMonthlyConsentNotProvided(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsMetricFrequencyNotFound(); } catch (Exception e) { log.info(e.getMessage()); }

        // Combined business use case: matches -> metrics
        log.log(Level.INFO, "MetricsFromMatch for Monthly : {0}", MatchesAndMetricsFlowExample.getMetricsMonthlyUsingMatchResults());
        log.log(Level.INFO, "MetricsFromMatch for Weekly: {0}", MatchesAndMetricsFlowExample.getMetricsWeeklyUsingMatchResults());

        // Benchmarks metrics examples
        log.log(Level.INFO, "FullyPopulatedBenchmarksMetrics: {0}", MetricsFlowExample.getFullyPopulatedBenchmarksMetrics());
        log.log(Level.INFO, "LowTransactionVolumeBenchmarksMetrics: {0}", MetricsFlowExample.getMerchantWithLowTransactionVolumeBenchmarksMetrics());

        // Benchmarks metrics error examples
        try { MetricsFlowExample.throwsBenchmarksMetricsNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsBenchmarksMetricsLocationNotFound(); } catch (Exception e) { log.info(e.getMessage()); }
        try { MetricsFlowExample.throwsBenchmarksMetricsConsentNotProvided(); } catch (Exception e) { log.info(e.getMessage()); }

        // Combined business use case: matches -> benchmarks metrics
        log.log(Level.INFO, "MetricsFromMatch: {0}", MatchesAndMetricsFlowExample.getBenchmarksMetricsUsingMatchResults());
    }
}
