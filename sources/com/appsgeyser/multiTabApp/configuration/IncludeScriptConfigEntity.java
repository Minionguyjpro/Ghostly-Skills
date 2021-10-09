package com.appsgeyser.multiTabApp.configuration;

import java.io.Serializable;

public class IncludeScriptConfigEntity implements Serializable {
    private final String file;
    private final String pattern;
    private final String regex;

    public IncludeScriptConfigEntity(String str, String str2, String str3) {
        this.file = str3;
        this.pattern = str;
        this.regex = str2;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getRegex() {
        return this.regex;
    }

    public String getFile() {
        return this.file;
    }
}
