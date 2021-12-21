package com.appsgeyser.multiTabApp.suggestions;

import java.util.ArrayList;

public interface SuggestionsListener {
    void onReceiveSuggestions(ArrayList<RemoteSuggestionItem> arrayList, String str);
}
