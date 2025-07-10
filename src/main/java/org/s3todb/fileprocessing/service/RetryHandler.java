package org.s3todb.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.adapter.ProviderAdapter;
import org.s3todb.logging.LoggingService;
import org.s3todb.util.Constants;
import org.s3todb.util.Utilities;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RetryHandler {
    @Autowired
    DataHandler dataHandler;
    @Autowired
    LoggingService loggingService;
    @Autowired
    Utilities util;

    private ScheduledExecutorService executor; // Allows delay

    // basic retry with logging when retry-count is exhausted
    public void retry(String line, ProviderAdapter adapter, Provider provider, String file, String fileType, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap, int retryCount) {
        int threadPoolCount = util.createCpuBoundThreadPool();
        this.executor = Executors.newScheduledThreadPool(threadPoolCount); // Thread-safe shared pool

        attemptSave(line, adapter, provider, fileType, languageIdMap, ratingCategoryIdMap, retryCount)
            .thenAccept(success -> {
                if (!success) {
                    loggingService.retryLogs(line, provider.getProviderId(), file);
                }
            });
    }

    private CompletableFuture<Boolean> attemptSave(String line, ProviderAdapter adapter, Provider provider,
                                                   String fileType, Map<String, Integer> languageIdMap,
                                                   Map<String, Integer> ratingCategoryIdMap, int retryCount) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return dataHandler.saveContent(line, adapter, provider, fileType, languageIdMap, ratingCategoryIdMap);
            } catch (Exception e) {
                return false;
            }
        }, executor).thenCompose(isSaved -> {
            if (isSaved) {
                return CompletableFuture.completedFuture(true);
            } else if (retryCount < Constants.LOG_RETRY_COUNT) {
                int nextRetry = retryCount + 1;
                long delay = (long) Math.pow(2, nextRetry); // exponential backoff (2s, 4s, 8s...)

                CompletableFuture<Boolean> delayedFuture = new CompletableFuture<>();
                executor.schedule(() -> {
                    attemptSave(line, adapter, provider, fileType, languageIdMap, ratingCategoryIdMap, nextRetry)
                            .whenComplete((result, ex) -> {
                                if (ex != null) delayedFuture.completeExceptionally(ex);
                                else delayedFuture.complete(result);
                            });
                }, delay, TimeUnit.SECONDS);

                return delayedFuture;
            } else {
                return CompletableFuture.completedFuture(false);
            }
        }).exceptionally(ex -> false);
    }
}
