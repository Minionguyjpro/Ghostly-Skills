package com.startapp.android.publish.adsCommon;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class SDKAdPreferences implements Serializable {
    private static final long serialVersionUID = 1;
    private String age = null;
    private Gender gender = null;

    /* compiled from: StartAppSDK */
    public enum Gender {
        MALE("m"),
        FEMALE("f");
        
        private String gender;

        private Gender(String str) {
            this.gender = str;
        }

        public String getGender() {
            return this.gender;
        }

        public String toString() {
            return getGender();
        }

        public static Gender parseString(String str) {
            for (Gender gender2 : values()) {
                if (gender2.getGender().equals(str)) {
                    return gender2;
                }
            }
            return null;
        }
    }

    public Gender getGender() {
        return this.gender;
    }

    public SDKAdPreferences setGender(Gender gender2) {
        this.gender = gender2;
        return this;
    }

    public String getAge() {
        return this.age;
    }

    public SDKAdPreferences setAge(int i) {
        this.age = Integer.toString(i);
        return this;
    }

    public SDKAdPreferences setAge(String str) {
        this.age = str;
        return this;
    }

    public String toString() {
        return "SDKAdPreferences [gender=" + this.gender + ", age=" + this.age + "]";
    }
}
