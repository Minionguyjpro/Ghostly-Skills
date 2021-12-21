package com.appsgeyser.multiTabApp.suggestions;

public class RemoteSuggestionItem implements SuggestionItem {
    private String text;

    public RemoteSuggestionItem(String str) {
        this.text = str;
    }

    public String getAutocompleteText() {
        return this.text;
    }
}
