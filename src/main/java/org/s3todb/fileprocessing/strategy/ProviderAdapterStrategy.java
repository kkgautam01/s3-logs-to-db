package org.s3todb.fileprocessing.strategy;

import org.s3todb.fileprocessing.adapter.ProviderAdapter;


public interface ProviderAdapterStrategy {
    ProviderAdapter getAdapter();

}
