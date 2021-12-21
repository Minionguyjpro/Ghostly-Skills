package com.tappx.sdk.android;

public final class AdRequest {

    /* renamed from: a  reason: collision with root package name */
    private String f640a = "native";
    private String b = null;
    private String c = null;
    private int d = -1;
    private int e = -1;
    private Gender f = Gender.UNKNOWN;
    private MaritalStatus g = MaritalStatus.UNKNOWN;
    private boolean h;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER,
        UNKNOWN
    }

    public enum MaritalStatus {
        SINGLE,
        LIVING_COMMON,
        MARRIED,
        DIVORCED,
        WIDOWED,
        UNKNOWN
    }

    public AdRequest age(int i) {
        this.e = i;
        return this;
    }

    public AdRequest gender(Gender gender) {
        this.f = gender;
        return this;
    }

    public int getAge() {
        return this.e;
    }

    public Gender getGender() {
        return this.f;
    }

    public String getKeywords() {
        return this.c;
    }

    public MaritalStatus getMaritalStatus() {
        return this.g;
    }

    public String getMediator() {
        return this.b;
    }

    public String getSdkType() {
        return this.f640a;
    }

    public int getYearOfBirth() {
        return this.d;
    }

    public boolean isUseTestAds() {
        return this.h;
    }

    public AdRequest keywords(String str) {
        this.c = str;
        return this;
    }

    public AdRequest maritalStatus(MaritalStatus maritalStatus) {
        this.g = maritalStatus;
        return this;
    }

    public AdRequest mediator(String str) {
        this.b = str;
        return this;
    }

    public AdRequest sdkType(String str) {
        this.f640a = str;
        return this;
    }

    public AdRequest useTestAds(boolean z) {
        this.h = z;
        return this;
    }

    public AdRequest yearOfBirth(int i) {
        this.d = i;
        return this;
    }
}
