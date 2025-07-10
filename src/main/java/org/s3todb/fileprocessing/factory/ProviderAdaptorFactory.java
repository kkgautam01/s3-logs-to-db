package org.s3todb.fileprocessing.factory;

import org.springframework.stereotype.Component;
import org.s3todb.fileprocessing.strategy.AgodaAdapterStrategy;
import org.s3todb.fileprocessing.strategy.ProviderAdapterStrategy;
import org.s3todb.util.Constants;

@Component
public class ProviderAdaptorFactory {
    public ProviderAdaptorFactory() {
    }

    public ProviderAdapterStrategy getStrategy(String providerName) {
        switch (providerName.toLowerCase()) {
            case Constants.AGODA -> {
                return new AgodaAdapterStrategy();
            }
            default -> throw new IllegalArgumentException("Unsupported provider: " + providerName);
        }
    }
}
