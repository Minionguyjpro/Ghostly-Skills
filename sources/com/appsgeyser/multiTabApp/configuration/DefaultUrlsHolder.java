package com.appsgeyser.multiTabApp.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

public class DefaultUrlsHolder {
    private static DefaultUrlsHolder instance;
    private ArrayList<String> domains;
    private ArrayList<String> urls;

    public static DefaultUrlsHolder getInstance() {
        if (instance == null) {
            instance = new DefaultUrlsHolder();
        }
        return instance;
    }

    private DefaultUrlsHolder() {
        this.urls = null;
        this.domains = null;
        this.urls = new ArrayList<>();
        this.domains = new ArrayList<>();
    }

    private String convertUrl(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        String trim = str.trim();
        return trim.charAt(trim.length() + -1) == '/' ? trim.substring(0, trim.length() - 1) : trim;
    }

    public boolean containsUrl(String str) {
        return this.urls.contains(convertUrl(str));
    }

    public boolean isDefaultUrl(String str) {
        if (str.startsWith("file://")) {
            return true;
        }
        return this.urls.contains(convertUrl(str));
    }

    public boolean isPermittedDomain(String str) {
        if (str.startsWith("file://")) {
            return true;
        }
        String _getDomainFromUrl = _getDomainFromUrl(str);
        if (_getDomainFromUrl == null || _getDomainFromUrl.equals("") || !_isDefaultHost(_getDomainFromUrl)) {
            return false;
        }
        return true;
    }

    public void addUrl(String str) {
        if (str != null) {
            String convertUrl = convertUrl(str);
            if (!containsUrl(convertUrl)) {
                this.urls.add(convertUrl);
            }
            String _getDomainFromUrl = _getDomainFromUrl(convertUrl);
            if (_getDomainFromUrl != null && !this.domains.contains(_getDomainFromUrl)) {
                this.domains.add(_getDomainFromUrl.toLowerCase());
            }
        }
    }

    private boolean _isDefaultHost(String str) {
        String lowerCase = str.toLowerCase();
        Iterator<String> it = this.domains.iterator();
        while (it.hasNext()) {
            if (lowerCase.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private String _getDomainFromUrl(String str) {
        URI uri;
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            uri = null;
        }
        if (uri != null) {
            return uri.getHost();
        }
        return null;
    }
}
