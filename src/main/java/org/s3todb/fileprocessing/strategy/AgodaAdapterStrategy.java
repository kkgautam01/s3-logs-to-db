package org.s3todb.fileprocessing.strategy;

import org.s3todb.fileprocessing.adapter.AgodaAdapter;
import org.s3todb.fileprocessing.adapter.ProviderAdapter;


public class AgodaAdapterStrategy implements ProviderAdapterStrategy {
    @Override
    public ProviderAdapter getAdapter() {
        return new AgodaAdapter();  // Return Agoda-specific adapter
    }
}
