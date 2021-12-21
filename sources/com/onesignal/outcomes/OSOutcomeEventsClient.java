package com.onesignal.outcomes;

import com.onesignal.OneSignalAPIClient;
import com.onesignal.outcomes.domain.OutcomeEventsService;

abstract class OSOutcomeEventsClient implements OutcomeEventsService {
    final OneSignalAPIClient client;

    OSOutcomeEventsClient(OneSignalAPIClient oneSignalAPIClient) {
        this.client = oneSignalAPIClient;
    }
}
