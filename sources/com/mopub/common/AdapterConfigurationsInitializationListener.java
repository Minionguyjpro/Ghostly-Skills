package com.mopub.common;

import java.util.Map;

interface AdapterConfigurationsInitializationListener extends OnNetworkInitializationFinishedListener {
    void onAdapterConfigurationsInitialized(Map<String, AdapterConfiguration> map);
}
