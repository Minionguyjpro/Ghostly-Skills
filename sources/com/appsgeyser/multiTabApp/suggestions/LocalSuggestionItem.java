package com.appsgeyser.multiTabApp.suggestions;

public class LocalSuggestionItem implements SuggestionItem {
    String title;
    String url;

    public LocalSuggestionItem(String str, String str2) {
        this.title = str == null ? "" : str;
        this.url = str2 == null ? "" : str2;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAutocompleteText() {
        return this.url;
    }
}
