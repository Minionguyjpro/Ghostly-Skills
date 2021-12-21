package com.appnext.ads.fullscreen;

import java.io.Serializable;
import java.util.HashMap;

public class RewardedServerSidePostback implements Serializable {
    private static final long serialVersionUID = 1;
    private String bo = "";
    private String bp = "";
    private String bq = "";
    private String br = "";
    private String bs = "";

    public RewardedServerSidePostback() {
    }

    public RewardedServerSidePostback(String str, String str2, String str3, String str4, String str5) {
        this.bo = str;
        this.bp = str2;
        this.bq = str3;
        this.br = str4;
        this.bs = str5;
    }

    public String getRewardsTransactionId() {
        return this.bo;
    }

    public void setRewardsTransactionId(String str) {
        this.bo = str;
    }

    public String getRewardsUserId() {
        return this.bp;
    }

    public void setRewardsUserId(String str) {
        this.bp = str;
    }

    public String getRewardsRewardTypeCurrency() {
        return this.bq;
    }

    public void setRewardsRewardTypeCurrency(String str) {
        this.bq = str;
    }

    public String getRewardsAmountRewarded() {
        return this.br;
    }

    public void setRewardsAmountRewarded(String str) {
        this.br = str;
    }

    public String getRewardsCustomParameter() {
        return this.bs;
    }

    public void setRewardsCustomParameter(String str) {
        this.bs = str;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, String> p() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("rewardsTransactionId", this.bo);
        hashMap.put("rewardsUserId", this.bp);
        hashMap.put("rewardsRewardTypeCurrency", this.bq);
        hashMap.put("rewardsAmountRewarded", this.br);
        hashMap.put("rewardsCustomParameter", this.bs);
        return hashMap;
    }
}
