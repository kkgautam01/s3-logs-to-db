package org.s3todb.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.adapter.ProviderAdapter;
import org.s3todb.fileprocessing.factory.ProviderAdaptorFactory;
import org.s3todb.fileprocessing.strategy.ProviderAdapterStrategy;
import org.s3todb.kafka.Producer;
import org.s3todb.util.FileType;
import org.s3todb.util.LogMessages;

import java.io.BufferedReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileProcessingService {
    @Autowired
    ProviderAdaptorFactory providerAdaptorFactory;
    @Autowired
    DataHandler dataHandler;


    @Autowired
    RetryHandler retryService;
    private static final Logger LOGGER = Logger.getLogger(FileProcessingService.class.getName());

    public void process(BufferedReader reader, Provider provider, String file,
                        Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap,Map<String, String> providerMap){
        ProviderAdapterStrategy providerSelectionStrategy = providerAdaptorFactory.getStrategy(provider.getName());
        ProviderAdapter adapter = providerSelectionStrategy.getAdapter();

        readFile(reader, adapter, provider, file, languageIdMap, ratingCategoryIdMap, providerMap);


    }

    public void readFile(BufferedReader reader, ProviderAdapter adapter,
                         Provider provider, String file, Map<String, Integer> languageIdMap,
                         Map<String, Integer> ratingCategoryIdMap, Map<String, String> providerMap){
        String line;
        try {
            String fileType = FileType.fromFileName(file);
            int retry =0;
            boolean isSaved;
            while ((line = reader.readLine()) != null) {
                isSaved = false;

                try {
                    isSaved = dataHandler.saveContent(line, adapter, provider,
                            fileType, languageIdMap, ratingCategoryIdMap);
                    Producer.sendData(line,adapter,provider,fileType, languageIdMap, ratingCategoryIdMap, 0, providerMap);

                }catch (Exception ignored) {}

                if (!isSaved) {
                    retryService.retry(line, adapter, provider, file, fileType,
                            languageIdMap, ratingCategoryIdMap, retry);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, LogMessages.COULD_NOT_PROCESS_THE_FILE + " : File : " + file);
        }
    }



}
