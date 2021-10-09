package com.appsgeyser.multiTabApp.storage;

public class BrowsingHistoryItem {
    private String date;
    private String id;
    private String title;
    private String url;

    public BrowsingHistoryItem(String str, String str2, String str3, String str4) {
        this.id = str;
        this.date = str2;
        this.title = str3;
        this.url = str4;
    }

    public String getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }
}
