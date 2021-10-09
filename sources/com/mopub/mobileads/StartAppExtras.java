package com.mopub.mobileads;

public class StartAppExtras {
    public static final String STARTAPP_EXTRAS_KEY = "startAppExtras";
    private String adTag;
    private Integer age;
    private String keywords;

    protected StartAppExtras() {
    }

    public String getAdTag() {
        return this.adTag;
    }

    public StartAppExtras setAdTag(String str) {
        this.adTag = str;
        return this;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public StartAppExtras setKeywords(String str) {
        this.keywords = str;
        return this;
    }

    public Integer getAge() {
        return this.age;
    }

    public StartAppExtras setAge(Integer num) {
        this.age = num;
        return this;
    }
}
