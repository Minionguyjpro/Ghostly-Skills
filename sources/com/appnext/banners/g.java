package com.appnext.banners;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.k;
import com.appnext.core.p;
import com.appnext.core.webview.AppnextWebView;
import com.mopub.common.Constants;
import com.mopub.network.ImpressionData;
import com.startapp.android.mediation.admob.StartAppNative;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends a {
    /* access modifiers changed from: private */
    public String adsid = "";
    /* access modifiers changed from: private */
    public boolean clicked = false;
    /* access modifiers changed from: private */
    public Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Handler refreshHandler = new Handler(Looper.getMainLooper());
    private BannerAdData selectedAd = null;
    /* access modifiers changed from: private */
    public ArrayList<String> shown = new ArrayList<>();
    protected WebView webView;

    /* access modifiers changed from: protected */
    public String getJSurl() {
        return "https://cdn.appnext.com/tools/sdk/banner/2.4.3/banner.min.js";
    }

    /* access modifiers changed from: protected */
    public String getTargetJSurl() {
        return "https://cdn.appnext.com/tools/sdk/banner/2.4.3/result.min.js";
    }

    /* access modifiers changed from: protected */
    public void inflateView(int i, AppnextAd appnextAd) {
        try {
            if (this.webView != null) {
                this.rootView.removeAllViews();
                this.webView.destroyDrawingCache();
                this.webView.destroy();
            }
            View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(getLayout(), this.rootView, false);
            WebView webView2 = new WebView(this.context.getApplicationContext());
            this.webView = webView2;
            ((ViewGroup) inflate).addView(webView2);
            this.webView.getLayoutParams().height = -1;
            this.webView.getLayoutParams().width = -1;
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.getSettings().setAppCacheEnabled(true);
            this.webView.getSettings().setDomStorageEnabled(true);
            this.webView.getSettings().setDatabaseEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                this.webView.getSettings().setMixedContentMode(0);
            }
            if (Build.VERSION.SDK_INT >= 17) {
                this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
            this.webView.setWebChromeClient(new WebChromeClient() {
                public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    StringBuilder sb = new StringBuilder("[");
                    sb.append(consoleMessage.messageLevel().name());
                    sb.append(":CONSOLE(");
                    sb.append(consoleMessage.lineNumber());
                    sb.append(")] \"");
                    sb.append(consoleMessage.message());
                    sb.append("\", source: ");
                    sb.append(consoleMessage.sourceId());
                    sb.append(" (");
                    sb.append(consoleMessage.lineNumber());
                    sb.append(")");
                    return true;
                }
            });
            this.webView.setWebViewClient(getWebViewClient());
            final String jSurl = getJSurl();
            AppnextWebView.u(this.context).a(jSurl, (AppnextWebView.c) new AppnextWebView.c() {
                public final void f(String str) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            g.this.loadWebview(jSurl, AppnextWebView.u(g.this.context).aj(jSurl));
                        }
                    });
                }

                public final void error(String str) {
                    new StringBuilder("error loading script ").append(str);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            g.this.loadWebview(jSurl, g.this.getFallbackScript());
                        }
                    });
                }
            });
            AppnextWebView.u(this.context).a(getTargetJSurl(), (AppnextWebView.c) null);
            this.webView.addJavascriptInterface(getWebInterface(), "Appnext");
            this.rootView.addView(inflate);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        if (getBannerSize().toString().equals(BannerSize.BANNER.toString())) {
            return R.layout.apnxt_banner;
        }
        if (getBannerSize().toString().equals(BannerSize.LARGE_BANNER.toString())) {
            return R.layout.apnxt_large_banner;
        }
        if (getBannerSize().toString().equals(BannerSize.MEDIUM_RECTANGLE.toString())) {
            return R.layout.apnxt_medium_rectangle;
        }
        throw new IllegalArgumentException("Wrong banner size " + getBannerSize().toString());
    }

    /* access modifiers changed from: protected */
    public void loadJS(String str) {
        new StringBuilder("loading js ").append(str);
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.loadUrl(str);
        }
    }

    /* access modifiers changed from: protected */
    public void loadWebview(String str, String str2) {
        try {
            URL url = new URL(str);
            this.webView.loadDataWithBaseURL(url.getProtocol() + "://" + url.getHost(), "<html><body><script>" + str2 + "</script></body></html>", (String) null, "UTF-8", (String) null);
        } catch (Throwable unused) {
        }
    }

    public void destroy() {
        super.destroy();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                try {
                    ((ViewGroup) g.this.webView.getParent()).removeView(g.this.webView);
                } catch (Throwable unused) {
                }
                if (g.this.webView != null) {
                    g.this.webView.destroyDrawingCache();
                    g.this.webView.destroy();
                }
            }
        });
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        Handler handler3 = this.refreshHandler;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public boolean isDestroyed() {
        return getBannerAd() == null;
    }

    /* access modifiers changed from: protected */
    public JSONObject getConfigParams() throws JSONException {
        String str;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vid", "2.5.1.472");
        jSONObject.put("tid", getBannerAd() == null ? "" : getBannerAd().getTID());
        if (getBannerAd() == null) {
            str = "";
        } else {
            str = getBannerAd().getAUID();
        }
        jSONObject.put("auid", str);
        jSONObject.put("osid", "100");
        jSONObject.put("tem_id", getBannerAd().getTemId(getSelectedAd()));
        jSONObject.put("id", getPlacementId());
        jSONObject.put("b_title", getButtonText(getSelectedAd()));
        jSONObject.put("creative", getCreativeType(getSelectedAd()) == 0 ? "video" : "static");
        jSONObject.put("cat", getSelectedAd().getCategories());
        jSONObject.put("pview", d.S().get("pview"));
        jSONObject.put("video_length", getAdRequest().getVideoLength());
        StringBuilder sb = new StringBuilder();
        sb.append(getAdRequest().isMute());
        jSONObject.put("mute", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getAdRequest().isAutoPlay());
        jSONObject.put("auto_play", sb2.toString());
        jSONObject.put("remove_poster_on_auto_play", d.S().get("remove_poster_on_auto_play"));
        jSONObject.put("remote_auto_play", true);
        jSONObject.put("did", this.adsid);
        jSONObject.put("devn", f.be());
        jSONObject.put("dosv", Build.VERSION.SDK_INT);
        jSONObject.put("dds", "0");
        jSONObject.put("ads_type", "banner");
        jSONObject.put(ImpressionData.COUNTRY, getSelectedAd().getCountry());
        jSONObject.put("gdpr", k.a((AppnextAd) getSelectedAd(), (p) d.S()));
        jSONObject.put("lang_settings", new JSONObject(com.appnext.core.a.b.bp().bq()).toString());
        String language = getLanguage();
        if (language == null || language.equals("")) {
            language = Locale.getDefault().getLanguage().toUpperCase();
        }
        jSONObject.put("lang", language);
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public String getFallbackScript() {
        new c();
        return "var Appnext=function(e){var t=e;return t.css='html,body{font-family:sans-serif;-webkit-text-size-adjust:100%;text-size-adjust:100%;height:100%;width:100%;padding:0 !important;margin:0 !important;overflow:hidden;font-size:100%;-moz-user-select:none;-webkit-user-select:none;user-select:none}#appnext{height:248px;width:298px;background:white;font-family:Arial,Helvetica,sans-serif;font-size:9px;font-weight:normal;font-style:normal;font-stretch:normal;line-height:normal;letter-spacing:normal;color:#979797;border:1px solid #d4d4d4;position:relative;z-index:10}#appnext .contianer{position:relative;width:100%;height:100%}#appnext .contianer>div{position:absolute}#appnext .contianer>div.wide_image{width:100%;height:151px;background-size:cover;background-repeat:no-repeat}#appnext .contianer>div.app_icon{width:42px;height:43px;top:164px;left:9px;background-size:contain;background-repeat:no-repeat}#appnext .contianer>div.app_title{width:225px;height:13px;font-size:12px;top:175px;left:59px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap}#appnext .contianer>div.app_desc{color:#989696;width:201px;height:32px;left:9px;bottom:0}#appnext .contianer>div.install{width:75px;height:21px;background-color:#689f38;font-size:10px;color:#ffffff;text-align:center;right:9px;bottom:10px;line-height:21px}#appnext.LARGE_BANNER{height:98px;width:318px}#appnext.LARGE_BANNER .contianer .wide_image{display:none}#appnext.LARGE_BANNER .contianer .app_icon{top:12px}#appnext.LARGE_BANNER .contianer .app_title{top:27px}#appnext.BANNER{height:48px;width:318px}#appnext.BANNER .contianer .wide_image{display:none}#appnext.BANNER .contianer .app_desc{display:none}#appnext.BANNER .contianer .app_icon{top:3px}#appnext.BANNER .contianer .app_title{width:169px;top:17px}#appnext.BANNER .contianer .install{top:14px;bottom:initial}#appnext.BANNER.gdpr .app_title{width:162px}#appnext.BANNER.gdpr .install{right:21px}#appnext .disclosure{position:absolute;top:1px;right:1px;height:15px;z-index:10000;display:table}#appnext .disclosure:after{content:\"\";width:11px;display:table-cell;background-repeat:no-repeat;background-size:100%;background-image:url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAMAAABEpIrGAAAAulBMVEVra2v///9ra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2tra2uD32R/AAAAPXRSTlMAAAECAwUGBwwNDhAbHCAhJygwPj9ARkdJUFlaYGJjcICHiImKi5CRoKizwsPExcrLzNvc3uDh4ujr8vP56NItIQAAATtJREFUeAGlk/9PwjAUxOtaBHQCc+yLIAgbiKAwGVCH2/3//5ax0rchTWbi/fTy8knbXO/YVY3+ArCTLDdeZ0WRrWeupXcVgI9SkNIR/w10E0BG/l2z5QTRHki654B3hBwIfbAYSBzdKuAXWN2witorFEEJ9D6x4OxMfIG8pwGRYKnfTbKWSMQJGOGjrff3j3SLxPgHsFIM9bYDPOt5iNRSQB8HQQcAL3oWB/QVECNipIenW5ojxAp4Q1ACk0mH5gCvCshglwDg0WwjU0CBphm4RlEL1F5BjzQB9MgZIjMQISajTADXRpHVBJDVXAFsDNnSW8/TTrbos5h4N3/3VujAOLkxME4ZudAUubAa2jCHHPBqaPPwPPbOFpBT3240bH+6B7bOZXF2IO0uiqMQd775rt5m7vKyev9v9xdMJTC1gou06wAAAABJRU5ErkJggg==\")}#appnext .disclosure.gdpr{width:13.50pt;height:13.50pt;top:0;right:0}#appnext .disclosure.gdpr:after{margin:0;height:100%;background-position:bottom;background-image:url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACcAAAAnCAMAAAC7faEHAAAAZlBMVEXNzMwArs1WusyTw8xEuM0Ir83Jy8zFy8y2yMxhvMwWsc2kxsx1v8wttM0dss0QsM2/ysyoxsyaxMyNwsyfxcyHwcyAwMxrvcxLucw3tsw+t80ytc0ks826ycyxx8yux8xovcxPucxOwgPRAAAA+0lEQVQ4y83Tx27DMBAE0CUpdvXuEpf//8lIngS+sOhmz4kQHiAsOUufje7UEXZvWD0ccJZtuYxZx9krxT3vEP6TdYhcdMYtJ0g3q6QTJK6QV5F2pGYHeeqSjkgvEnI1SUfUckBp24TTthC3ArIpVcThYQyNFWQ9xdy6HfYZhhqyGsOul+z8+psqG8izCTkyvSKktRjdtQHXOVb8Q/KAQ8A9t4PHN/0Akybg9ivpac/k/ibxlHC+gnITUdyZ9V2yuBMPCfZEaWMOCp2JOwTrl3GoQNahUlE382qmct/OW3bfvCr5eGAvh2N7ftEZZ9FeRZl4iavIxghNX5lfdnkJssV7DcsAAAAASUVORK5CYII=\")} ',t.template='<div class=\"disclosure\"></div> <template id=\"app_template\">     <div class=\"app_container\">         <div class=\"wide_image\"></div>         <div class=\"app_icon\"></div>         <div class=\"app_title\"></div>         <div class=\"app_desc\"></div>         <div class=\"install\"></div>     </template>   ',t.vid=t.vid||\"1\",t.tid=t.tid||\"301\",t.ads_type=\"banner\",t.osid=t.osid||\"100\",t.auid=t.auid||\"100\",t}(Appnext||{}),Appnext=function(e){function t(e){n.Layout.Disclosure.addEventListener(\"click\",function(t){t.stopPropagation();var o=Math.floor(10*Math.random())+e.urlApp.match(\"[?&]e=([^&]+)\")[1]+Math.floor(10*Math.random()),i=\"https://www.appnext.com/privacy_policy/index.html?z=\"+o+\"&geo=\"+e.country;\"true\"==n.gdpr&&(i+=\"&edda=1\"),n.redirect(i)},!1),\"true\"==n.gdpr?n.Layout.Disclosure.classList.add(\"gdpr\"):\"430\"==n.auid&&n.Layout.Footer.appendChild(n.Layout.Disclosure)}var n=e;n.id=n.android_id||n.id,n.osType=function(){var e=navigator.userAgent||navigator.vendor||window.opera;return e.match(/Opera Mini/i)||e.match(/Windows/i)?\"unknown\":e.match(/iPad/i)||e.match(/iPhone/i)||e.match(/iPod/i)||e.match(/iOS/i)?(n.did=n.IDFA?n.IDFA:\"\",\"iOS\"):e.match(/Android/i)?(n.did=n.AAID?n.AAID:\"\",\"Android\"):\"unknown\"}(),\"iOS\"==n.osType&&(n.id=n.ios_id,n.osid=\"200\"),n.parent_url||(n.parent_url=window.location),\"true\"==n.use_skip&&(n.ad_server=\"false\"),\"\"!=n.auto_play&&\"true\"!=n.auto_play&&void 0!==n.auto_play||\"static\"==n.creative||-1!=navigator.userAgent.indexOf(\"SamsungBrowser\")||(n.auto_play_default=!0,n.auto_play=\"true\",n.mute_default=n.mute,n.mute=\"true\"),n.setCookie=function(e,t,n){if(navigator.cookieEnabled){var o=new Date;o.setHours(o.getHours()+n);var i=\"expires=\"+o.toGMTString()+\";path=/\";document.cookie=e+\"=\"+t+\"; \"+i}},n.getCookie=function(e){if(!navigator.cookieEnabled)return!1;for(var t=e+\"=\",n=document.cookie.split(\";\"),o=0;o<n.length;o++){for(var i=n[o];\" \"==i.charAt(0);)i=i.substring(1);if(0==i.indexOf(t))return i.substring(t.length,i.length)}return!1},n.removeCookies=function(e){for(var t=0;t<e.length;t++)n.setCookie(e[t].bannerId,!0,-6)},n.timstamp=Date.now(),n.API=function(){function e(e,t,n,o,i,a){var r=\"xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx\".replace(/[xy]/g,function(e){var t=16*Math.random()|0;return(\"x\"==e?t:3&t|8).toString(16)}),s=\"onSuccessCB_\"+r;window[s]=function(e){return e||!0}(t);var d=document.createElement(\"script\");e+=~e.indexOf(\"?\")?\"&\":\"?\",e+=\"callback=\"+s,d.src=e,d.className=\"appnext_cb\",d.type=void 0!==o&&o?o:\"text/javascript\",d.async=!(void 0===i||!i)&&i;try{document.body?document.body.appendChild(d):document.head.appendChild(d)}catch(e){return!!n&&n(e)}}var t={cnt:10,pimp:1,vs:1,igroup:\"true\"==n.ad_server?\"mweb2\":\"mweb\"},o={offerWallApi:\"https://global.appnext.com/offerwallapi.aspx\",log:\"https://admin.appnext.com/tp12.aspx\",gpi:\"https://admin.appnext.com/gpi.aspx\"};return{buildUrl:function(e,t){var n=[];for(var o in t)n.push(encodeURIComponent(o)+\"=\"+encodeURIComponent(t[o]));return e+\"?\"+n.join(\"&\")},loadAds:function(i){n.lang=navigator.language?navigator.language.slice(0,2):\"\";var a={id:n.id,cat:n.cat,pbk:n.pbk,cnt:n.cnt||t.cnt,igroup:t.igroup,vid:n.vid,tid:n.tid,osid:n.osid,auid:n.auid,pimp:t.pimp,vs:t.vs,ext:n.ext||\"\",did:n.did||\"\",devn:n.devn||\"\",dosv:n.dosv||\"\",dds:n.dds||\"\",dct:n.dct||\"\",subid:n.subid||\"\",ip:n.ip||\"\",did:n.did,lang:n.lang,uAgent:n.uAgent||\"\",packageId:n.appId||\"\"},r=o.offerWallApi;e(this.buildUrl(r,a),i,function(e){n.API.log(n.API.TP12.LoadAdsError,JSON.stringify(e).slice(0,250))},null,!1)},moat:function(e){return},fq:function(e){return},setGPI:function(){return},log:function(t,i,a){if(t==n.API.TP12.Play||t==n.API.TP12.Ended){var r={tid:n.tid,vid:n.vid,osid:n.osid,auid:n.auid,pid:n.id,bid:a?a.bannerId:0,cid:a?a.campaignId:0,session_id:i?encodeURIComponent(i):null,ref:encodeURIComponent(t),ads_type:n.ads_type},s=o.log;e(this.buildUrl(s,r),function(e){return e},null,null,!0)}},getRequest:function(t){e(t,null,null,null,!0)},notifyImpression:function(t,o,i){n.DFP_IMP_MACRO&&(t.pixelImp=n.DFP_IMP_MACRO+t.pixelImp);var a=function(t,o,i){return function(){n.Layout.isVisible(o)&&(e(t,null,null,null,!0),i&&i())}}(t.pixelImp,o,i);return n.API.fq(t),setTimeout(a,2e3)},getStyleUrl:function(){return o.css},postView:function(e){if(\"false\"!=n.pview){var t=e.urlApp+\"&ox=0\";if(\"true\"==n.ad_server){var o=document.createElement(\"img\");return o.src=t,o.height=0,o.width=0,o.style.position=\"absolute\",o.style.opacity=\"0\",void n.Layout.Container.appendChild(o)}var i=\"true\"==n.ad_server?1:navigator.userAgent.indexOf(\"Chrome\")>-1;if(\"iOS\"==n.osType||i){var a=document.createElement(\"link\");a.rel=\"stylesheet\",a.type=\"text/css\",a.href=t,document.head.appendChild(a)}}},TP12:{Play:\"play_video\",NoAds:\"no_ad_to_show\",Paused:\"video_paused\",Resumed:\"video_resumed\",VideoError:\"error_video\",Ended:\"video_ended\",AdDisplayed:\"ad_displayed\",Canplay:\"can_play\",Init:\"init\",Timeout:\"timeout\",AdClosed:\"ad_closed\",VideoClosed:\"video_closed_page2\",OfferWallApi:\"offerWallApi\",LoadAdsError:\"error_loadAds\",ParseAdsError:\"error_parseAds\",ErrorUserAgent:\"error_userAgent\",DataReady:\"data_ready\",OfferWallApiError:\"error_offerWallApi\",TTS:\"times_to_show\",PostView:\"post_view\",GotoStore:\"open_store\",Version:n.ver||\"TEST\"},Error:{NoAds:\"NO_ADS\",UnknownUserAgent:\"UA_ERROR\",BadRequest:\"NO_ADS\",Other:\"NO_ADS\"}}}(),n.Layout=function(e){var t=n.template,o={Video:\"#video\",Video_Container:\".video\",App_Image:\"#main_app_img\",Title:\".main_app_title\",Rate_Number:\".rate\",Rating:\".rating\",Downloads:\".downloads\",Downloads_Number:\".downloads_number\",Description:\".desc\",Install_Button:\".install\",Skip:\".skip\",SkipText:\".skipText\",Close_Button:\".close_button\",More_Apps:\".more_apps\",More_Apps_Section:\".suggested_apps\",Small_App_Template:\"#app_template\",Click_Section:\"#click_section\",Footer:\".footer\",Header:\".header\",Disclosure:\".disclosure\",Sound_Button:\".sound_button\"},i=document.createElement(\"div\");i.id=\"appnext\",i.className=\"appnext\";var a,r,s,d=screen.width<screen.height?screen.height:screen.width,l={isLoaded:!1,isVisible:function(e){var t=e.getBoundingClientRect(),n=Math.max(document.documentElement.clientHeight,window.innerHeight);return!(t.bottom<0||t.top-n>=0)&&this.isLoaded},calcHeight:function(){if(this.isLoaded){var e=document.documentElement.clientWidth,t=document.documentElement.clientHeight,n=(this.Click_Section&&this.Click_Section.offsetHeight,this.Footer?this.Footer.offsetHeight:0),o=this.Header?this.Header.offsetHeight:0,i=180*e/320;this.Video_Container.style.height=i+\"px\",this.Video.style.height=i+\"px\";var a=t-this.More_Apps_Section.offsetTop-n-o;this.More_Apps_Section.style.height=a+\"px\",d<500&&this.Container.classList.add(\"min\"),setTimeout(function(){window.scrollTo(0,1)},600),setTimeout(function(){window.scrollTo(0,1)},1e3),window.scrollTo(0,1)}},resize:function(){n.Layout.calcHeight(),setTimeout(n.Layout.calcHeight.call(n.Layout),100)},loadStyle:function(){if(!this.isLoaded){this.isLoaded=!0,i.innerHTML=t;var e=document.createElement(\"meta\");e.name=\"viewport\",e.content=\"user-scalable=1\";var o=document.querySelectorAll(\"meta[name='viewport']\");s=o[o.length-1]||e,r=document.createElement(\"meta\"),r.name=\"viewport\",r.content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0\";var d=n.css||\"\";a=document.createElement(\"style\"),a.type=\"text/css\",a.innerHTML=d,document.head.appendChild(a),document.head.appendChild(r)}},destroy:function(e){if(this.isLoaded){this.isLoaded=!1,document.head.removeChild(r),document.body.removeChild(this.Container),document.head.removeChild(this.Style),document.head.appendChild(s);for(var t=document.querySelectorAll(\".appnext_cb\"),o=0;o<t.length;o++)document.body.removeChild(t[o]);return window.removeEventListener(\"resize\",n.Layout.resize),e}},get Container(){return i},get Style(){return a},set Style(e){a=e},getAppTemplate:function(){var e={},t=document.createElement(\"div\");t.innerHTML=l.Small_App_Template.innerHTML;var n={App_Image:\".icon img\",Title:\".title\",Rate_Number:\".rate\",Rating:\".rating\",Description:\".desc\",Click_Section:\".app_container\",Container:\".app_container\",Install_Button:\".install_btn\"};for(var o in n)!function(n,o){Object.defineProperty(e,n,{get:function(){return t.querySelector(o[n])||document.createElement(\"div\")},enumerable:!0})}(o,n);return e}};for(var p in o)!function(e){Object.defineProperty(l,e,{get:function(){return i.querySelector(o[e])||document.createElement(\"div\")},enumerable:!0})}(p);return l}(n.API);var o=function(){function e(){var e=new Date(Date.now()+6e4*parseInt(n.times_to_show_reset));localStorage.setItem(\"appnext_tts_exp_time\",e.getTime()),localStorage.setItem(\"appnext_tts\",n.times_to_show)}try{if(n.times_to_show){\"\"==n.times_to_show_reset&&(n.times_to_show_reset=1);var t=localStorage.getItem(\"appnext_tts\");if(!t)return e(),!0;var o=localStorage.getItem(\"appnext_tts_exp_time\");if(Date.now()<o){if(0==(t-=1))return!1;localStorage.setItem(\"appnext_tts\",t)}else localStorage.removeItem(\"appnext_tts\"),localStorage.removeItem(\"appnext_tts_exp_time\"),e()}return!0}catch(e){return!0}};return n.getStoreDownloadsText=function(e){var t=parseInt(e);return e.length>8?t/1e6+\"M\":e.length>4?t/1e3+\"K\":t},n.setTimeout=function(){var e=parseInt(n.timeout);!isNaN(e)&&n.timeout>0&&setTimeout(function(){n.API.log(n.API.TP12.Timeout),n.Layout.destroy(\"timeout\")},1e3*e)},n.gtsWindow=null,n.open=function(e){if(null==n.gtsWindow){n.Layout.Video.pause(),n.API.log(n.API.TP12.GotoStore),n.DFP_CLICK_MACRO&&(e.urlApp=n.DFP_CLICK_MACRO+e.urlApp);var t=e.urlApp;n.gtsWindow=window.open(t,\"_top\");var o=function(e){return function(){document.hidden&&(e.gtsWindow=null,clearInterval(e.gtsInterval))}}(n);n.gtsInterval=setInterval(o,10);var i=function(e){return function(){e.gtsWindow=null,clearInterval(e.gtsInterval)}}(n);setTimeout(i,1500)}},n.redirect=function(e){window.open(e,\"_top\")},n.setParams=function(e){for(key in e)n[key]=decodeURIComponent(e[key]);\"true\"==n.use_skip&&(n.ad_server=\"false\"),\"false\"!=n.ad_server&&(n.ad_server=\"true\")},n.bindVideoEvents=function(e,t,o,i){function a(){t.src=d,o.removeEventListener(\"click\",a,!1)}function r(){this.currentTime>0||(o.classList.add(\"waiting\"),t.removeEventListener(\"loadeddata\",s))}function s(){o.classList.remove(\"waiting\")}var d=\"\";switch(n.video_length){case\"15\":d=e.urlVideo||e.urlVideoHigh||e.urlVideo30Sec||e.urlVideo30SecHigh;break;case\"30\":d=e.urlVideo30Sec||e.urlVideo30SecHigh||e.urlVideo||e.urlVideoHigh;break;default:d=e.urlVideo||e.urlVideo30Sec||e.urlVideoHigh||e.urlVideo30SecHigh}if(\"\"==d||\"static\"==n.creative)return o.classList.remove(\"play\"),t.style.display=\"none\",!1;if(\"static\"!=n.creative&&\"\"!=d){\"static\"!=n.creative&&\"\"!=d&&(\"true\"==n.auto_play?(o.classList.remove(\"play\"),t.autoplay=!0,\"600\"==n.osid&&(t.autoplay=!1),a()):o.addEventListener(\"click\",a)),\"true\"==n.mute&&i.classList.add(\"mute_off\"),i.addEventListener(\"click\",function(e){e.stopPropagation(),this.classList.contains(\"mute_off\")?(this.classList.remove(\"mute_off\"),t.muted=!1):(this.classList.add(\"mute_off\"),t.muted=!0)}),t.addEventListener(\"ended\",function(){o.classList.add(\"play\"),n.API.log(n.API.TP12.Ended,null,e)}),t.addEventListener(\"error\",function(e){var t=e.target.error?e.target.error.code:0,o=\"\";switch(t){case 1:o=\"MEDIA_ERR_ABORTED\";break;case 2:o=\"MEDIA_ERR_NETWORK\";break;case 3:o=\"MEDIA_ERR_DECODE\";break;case 4:o=\"MEDIA_ERR_SRC_NOT_SUPPORTED \"}n.API.log(n.API.TP12.VideoError,this.src+\"_ERR_CODE_\"+t+\"_MSG_\"+o+\"_USER_AGENT_\"+navigator.userAgent)});var l=!1;t.addEventListener(\"waiting\",r),t.addEventListener(\"loadstart\",r),t.addEventListener(\"loadeddata\",s),t.addEventListener(\"canplay\",function(){s();var t=Date.now()-n.timstamp;n.API.log(n.API.TP12.Canplay,t.toString(),e)}),o.addEventListener(\"click\",function(e){this.classList.contains(\"play\")?(o.style.backgroundImage=\"\",t.play()):t.pause()}),t.addEventListener(\"pause\",function(){o.classList.remove(\"waiting\"),o.classList.add(\"play\"),this.currentTime!=this.duration&&n.API.log(n.API.TP12.Paused,null,e)}),t.addEventListener(\"play\",function(){o.classList.remove(\"play\"),i.style.display=\"block\",0==this.currentTime&&0==l?(l=!0,n.API.log(n.API.TP12.Play,null,e)):n.API.log(n.API.TP12.Resumed,null,e)})}return!0},n.bindMainAppEvents=function(e){n.API.postView(e),n.Layout.App_Image.addEventListener(\"load\",function(){n.API.notifyImpression(e,this);var t=Date.now()-n.timstamp;n.API.log(n.API.TP12.AdDisplayed,t.toString(),e)}),n.Layout.Click_Section.addEventListener(\"click\",function(){n.open(e)}),n.Layout.Install_Button.addEventListener(\"click\",function(t){t.stopPropagation(),n.open(e)})},n.filterByCreative=function(e){for(var t=[],o=0;o<e.length;o++){var i=e[o];switch(i.index=o,n.creative){case\"video\":\"\"==i.urlVideo&&\"\"==i.urlVideoHigh&&\"\"==i.urlVideo30Sec&&\"\"==i.urlVideo30SecHigh||t.push(i);break;case\"static\":\"\"!=i.urlImgWide&&t.push(i);break;default:\"\"==i.urlVideo&&\"\"==i.urlVideoHigh&&\"\"==i.urlVideo30Sec&&\"\"==i.urlVideo30SecHigh&&\"\"==i.urlImgWide||t.push(i)}}return 0==t.length&&(n.API.log(n.API.TP12.NoAds),n.error(n.API.Error.NoAds),n.Layout.destroy(n.API.TP12.NoAds)),t},n.getMainApp=function(e){var t=n.filterByCreative(e);if(0==t.length)return!1;var o=!1,i=!1;if(0==t.length)return!1;for(var a=0;a<t.length;a++){var r=t[a];{if(!n.getCookie(r.bannerId)){o=r;break}i=!0}}return!o&&i&&(n.removeCookies(t),o=t[0]),n.setCookie(o.bannerId,!0,6),e.splice(o.index,1),o},n.parseApp=function(e){\"true\"==n.ad_server&&(e=n.replaceLinkSrc(e));var t=n.Layout.getAppTemplate();return t.App_Image.src=e.urlImg,t.Title.textContent=e.title,t.Description.textContent=e.desc,0!=e.storeRating?t.Rate_Number.textContent=e.storeRating:t.Rating.style.display=\"none\",t.Click_Section.addEventListener(\"click\",function(){n.open(e)}),t.Install_Button.textContent=e.buttonText||\"Install\",t.Container},n.parseMainApp=function(e){\"true\"==n.ad_server&&(e=n.replaceLinkSrc(e)),\"\"!=e.urlImgWide&&(n.Layout.Video_Container.style.backgroundImage=\"url('\"+e.urlImgWide+\"')\"),n.Layout.App_Image.src=e.urlImg,\"true\"!=n.mute&&1!=n.mute||(n.Layout.Video.muted=!0),n.Layout.Title.textContent=e.title,0!=e.storeRating?n.Layout.Rate_Number.textContent=e.storeRating:n.Layout.Rate_Number.style.display=\"none\",\"Android\"==n.osType&&\"0\"!=e.storeDownloads?n.Layout.Downloads_Number.textContent=n.getStoreDownloadsText(e.storeDownloads):n.Layout.Downloads.style.display=\"none\",n.Layout.Description.textContent=e.desc,n.Layout.Install_Button.textContent=n.b_title||\"Install\",n.b_color&&(n.Layout.Install_Button.style.background=\"#\"+n.b_color),n.skip_title&&(n.Layout.SkipText.textContent=n.skip_title),\"false\"==n.show_rating&&(n.Layout.Downloads.style.display=\"none\",n.Layout.Rating.style.display=\"none\"),\"false\"==n.show_desc&&(n.Layout.Description.style.display=\"none\")},n.setContent=function(e){function t(){var e=Date.now()-n.timstamp;n.API.log(n.API.TP12.AdClosed,e.toString()),n.Layout.destroy(\"close\"),n.skip_url&&window.open(n.skip_url)}var o=n.getMainApp(e);if(0==o)return n.Layout.destroy(),void n.error(n.API.Error.Other);var i=n.bindVideoEvents(o,n.Layout.Video,n.Layout.Video_Container,n.Layout.Sound_Button);n.parseMainApp(o),n.bindMainAppEvents(o);for(var a=e.length>6?6:e.length,r=0;r<a&&r!=n.ads_to_show;r++)!function(e){var t=n.parseApp(e);n.Layout.More_Apps_Section.appendChild(t)}(e[r]);n.auto_play_default&&i&&(n.Layout.Sound_Button.style.display=\"block\"),\"true\"==n.ad_server?(n.Layout.Close_Button.addEventListener(\"click\",t),n.Layout.Skip.style.display=\"none\"):(n.Layout.Skip.addEventListener(\"click\",t),n.Layout.Close_Button.style.display=\"none\")},n.replaceLinkSrc=function(e){var t=\"cdn3.appnext.com\",n=\"appnext.hs.llnwd.net\",o=\"appnext-a.akamaihd.net\";return e.urlImg=e.urlImg.replace(n,t).replace(o,t),e.urlImgWide=e.urlImgWide.replace(n,t).replace(o,t),e.urlVideo=e.urlVideo.replace(n,t).replace(o,t),e.urlVideo30Sec=e.urlVideo30Sec.replace(n,t).replace(o,t),e.urlVideo30SecHigh=e.urlVideo30SecHigh.replace(n,t).replace(o,t),e.urlVideoHigh=e.urlVideoHigh.replace(n,t).replace(o,t),e},n.parseAds=function(e){if(\"false\"!=n.ad_server&&(n.ad_server=\"true\"),void 0!==e.rnd||0==Object.keys(e).length)return n.API.log(n.API.TP12.OfferWallApiError,e.rnd[0].cb),n.Layout.destroy(e.rnd.cb),n.error(n.API.Error.Other),!1;var o=e.apps;if(n.parent_url)for(var i=0;i<o.length;i++)o[i].urlApp+=\"&r=\"+encodeURIComponent(n.parent_url);n.Layout.loadStyle(),document.body.appendChild(n.Layout.Container),n.setContent(o),n.API.log(n.API.TP12.DataReady),t(o[0]),window.addEventListener(\"resize\",n.Layout.resize,!1,!0),setTimeout(function(){n.Layout.resize()},50),setTimeout(function(){n.Layout.resize()},600),n.setTimeout()},n.error=\"function\"==typeof n.onError?n.onError:function(){},n.load=function(){return!!n.id&&(\"unknown\"==n.osType?(n.API.log(n.API.TP12.ErrorUserAgent,navigator.userAgent),n.error(n.API.Error.UnknownUserAgent),!1):o()?(n.API.loadAds(n.parseAds),n.API.setGPI(),!0):(n.API.log(n.API.TP12.TTS,n.times_to_show+\"_\"+n.times_to_show_reset),!1))},n}(Appnext),Appnext=function(e){function t(e){!function(e){var t=o.parseApp(e);t.id=\"bid\"+e.bannerId,o.Layout.Container.appendChild(t)}(e)}function n(e){o.Layout.Disclosure.addEventListener(\"click\",function(t){t.stopPropagation();var n=Math.floor(10*Math.random())+e.urlApp.match(\"[?&]e=([^&]+)\")[1]+Math.floor(10*Math.random()),i=\"https://www.appnext.com/privacy_policy/index.html?z=\"+n+\"&geo=\"+e.country;\"true\"==o.gdpr&&(i+=\"&edda=1\"),o.redirect(i)},!1),\"true\"==o.gdpr&&(o.Layout.Disclosure.classList.add(\"gdpr\"),o.Layout.Container.classList.add(\"gdpr\"))}if(e){var o=e;o.Layout.destroy,o.setContent,o.parseAds;return o.redirect=function(e){o.openLink(e)},o.open=function(e){o.isOpen=!0,o.openStore(JSON.stringify(e))},o.loadBanner=function(e,i){console.log(i),o.Layout.loadStyle(),document.body.appendChild(o.Layout.Container),t(e),n(e),o.Layout.Container.classList.add(i)},o.parseApp=function(e){function t(t){t.preventDefault(),t.stopPropagation(),o.open(e)}var n=o.Layout.getAppTemplate(),i=document.createElement(\"div\");i.classList.add(\"contianer\"),i.innerHTML=n.Container.innerHTML;var a=i.querySelector(\".wide_image\"),r=i.querySelector(\".app_icon\"),s=i.querySelector(\".app_title\"),d=i.querySelector(\".install\"),l=i.querySelector(\".app_desc\");return r.style.backgroundImage=\"url('\"+e.urlImg+\"')\",s.textContent=e.title,e.desc.length>75?l.textContent=e.desc.slice(0,70)+\"...\":l.textContent=e.desc,o.b_title.length>12&&(o.b_title=o.b_title.slice(0,12)),d.textContent=o.b_title||\"Install\",o.b_color&&(d.style.background=\"#\"+o.b_color),a.style.backgroundImage=\"url('\"+e.urlImgWide+\"')\",i.addEventListener(\"click\",t),i},o.Layout.calcHeight=function(){},o.setContent=function(e){for(var i=0;i<e.length;i++)\"\"==e[i].urlImgWide&&(e[i].urlImgWide=e[i].urlImg,e[i].isIconAsWideImg=!0);if(e=o.filterByCreative(e),e.length){for(var a=0,r=[],s=[],i=0;i<e.length;i++)o.getCookie(e[i].bannerId)?s.push(e[i]):r.push(e[i]);0==r.length&&o.removeCookies(s);for(var d=r.concat(s),i=0;i<d.length&&i<1;i++)n(d[i]),t(d[i]),a++}},o}}(Appnext);";
    }

    /* access modifiers changed from: protected */
    public BannerAdData getSelectedAd() {
        if (this.selectedAd == null || !super.getSelectedAd().getBannerID().equals(this.selectedAd.getBannerID())) {
            BannerAdData bannerAdData = new BannerAdData(super.getSelectedAd());
            this.selectedAd = bannerAdData;
            bannerAdData.setImpressionURL(this.selectedAd.getImpressionURL() + "&tem_id=" + getBannerAd().getTemId(this.selectedAd));
            BannerAdData bannerAdData2 = this.selectedAd;
            bannerAdData2.setAppURL(this.selectedAd.getAppURL() + "&tem_id=" + getBannerAd().getTemId(this.selectedAd));
        }
        return this.selectedAd;
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (i == 0 && this.clicked) {
            this.clicked = false;
            openResultPage(false);
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new b();
    }

    /* access modifiers changed from: protected */
    public a getWebInterface() {
        return new a();
    }

    public class a {
        @JavascriptInterface
        public void destroy(String str) {
        }

        @JavascriptInterface
        public void jsError(String str) {
        }

        public a() {
        }

        @JavascriptInterface
        public void openStore(String str) {
            g.this.handler.removeCallbacksAndMessages((Object) null);
            if (!g.this.isDestroyed()) {
                g.this.handler.post(new Runnable() {
                    public final void run() {
                        if (!g.this.getSelectedAd().getWebview().equals("0")) {
                            g.this.click();
                            return;
                        }
                        char c = 65535;
                        if (g.this.getSelectedAd().getRevenueType().equals("cpi")) {
                            d S = d.S();
                            String lowerCase = S.get(g.this.getBannerSize().toString() + "_cpiActiveFlow").toLowerCase();
                            StringBuilder sb = new StringBuilder();
                            sb.append(g.this.getBannerSize().toString());
                            sb.append("_cpiActiveFlow ");
                            sb.append(lowerCase);
                            switch (lowerCase.hashCode()) {
                                case 97:
                                    if (lowerCase.equals("a")) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                                case 98:
                                    if (lowerCase.equals("b")) {
                                        c = 1;
                                        break;
                                    }
                                    break;
                                case 99:
                                    if (lowerCase.equals("c")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                                case 100:
                                    if (lowerCase.equals("d")) {
                                        c = 3;
                                        break;
                                    }
                                    break;
                            }
                            if (c != 0) {
                                if (c == 1) {
                                    boolean unused = g.this.clicked = true;
                                } else if (c == 2) {
                                    g.this.openResultPage(false);
                                    return;
                                }
                                g.this.click();
                                return;
                            }
                            g.this.openResultPage(true);
                            return;
                        }
                        d S2 = d.S();
                        String lowerCase2 = S2.get(g.this.getBannerSize().toString() + "_cpcActiveFlow").toLowerCase();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(g.this.getBannerSize().toString());
                        sb2.append("_cpcActiveFlow ");
                        sb2.append(lowerCase2);
                        int hashCode = lowerCase2.hashCode();
                        if (hashCode != 97) {
                            if (hashCode == 98 && lowerCase2.equals("b")) {
                                c = 1;
                            }
                        } else if (lowerCase2.equals("a")) {
                            c = 0;
                        }
                        if (c == 0) {
                            boolean unused2 = g.this.clicked = true;
                        }
                        g.this.click();
                    }
                });
            }
        }

        @JavascriptInterface
        public void openLink(String str) {
            if (!g.this.isDestroyed()) {
                g.this.openLink(str);
            }
        }

        @JavascriptInterface
        public void logSTP(String str, String str2) {
            f.a((Ad) g.this.getBannerAd(), (AppnextAd) g.this.getSelectedAd(), str, str2, (p) d.S());
        }
    }

    /* access modifiers changed from: private */
    public void openResultPage(boolean z) {
        Intent intent = new Intent(this.context, BannerActivity.class);
        intent.putExtra("placement", getBannerAd().getPlacementID());
        intent.putExtra("postback", getBannerAd().getPostback());
        intent.putExtra(StartAppNative.EXTRAS_CATEGORY, getBannerAd().getCategories());
        intent.putExtra("clicked", getSelectedAd().getBannerID());
        intent.putExtra("selected", getSelectedAd());
        intent.putExtra("size", getBannerSize().toString());
        intent.putExtra("shouldClose", z);
        intent.setFlags(65536);
        this.context.startActivity(intent);
    }

    public class b extends WebViewClient {
        public b() {
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (!str.startsWith(Constants.HTTP)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            webView.loadUrl(str);
            return true;
        }

        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            g.this.pageFinished();
        }
    }

    /* access modifiers changed from: private */
    public void pageFinished() {
        if (!isDestroyed()) {
            new Thread(new Runnable() {
                public final void run() {
                    g gVar = g.this;
                    String unused = gVar.adsid = f.b(gVar.context, false);
                    g.this.handler.removeCallbacksAndMessages((Object) null);
                    g.this.handler.post(new Runnable() {
                        public final void run() {
                            try {
                                g.this.loadJS("javascript:(function() { try { Appnext.Layout.destroy('internal'); } catch(err){ Appnext.jsError(err.message); }})()");
                                g gVar = g.this;
                                gVar.loadJS("javascript:(function() { try { Appnext.setParams(" + g.this.getConfigParams().toString() + "); } catch(err){ Appnext.jsError(err.message); }})()");
                                g gVar2 = g.this;
                                gVar2.loadJS("javascript:(function() { try { Appnext.loadBanner(" + new JSONObject(g.this.getSelectedAd().getAdJSON()).toString() + ",'" + g.this.getBannerSize().toString() + "'); } catch(err){ Appnext.jsError(err.message); }})()");
                                g.this.shown.add(g.this.getSelectedAd().getBannerID());
                            } catch (Throwable unused) {
                            }
                        }
                    });
                }
            }).start();
        }
    }

    private void activateAutoRefresh() {
        int i;
        d S = d.S();
        try {
            i = Integer.parseInt(S.get(getBannerSize().toString().toLowerCase() + "_ar"));
        } catch (Throwable unused) {
            i = 10;
        }
        if (Boolean.parseBoolean(S.get("_arFlag")) && i > 0) {
            this.refreshHandler.postDelayed(new Runnable() {
                public final void run() {
                    try {
                        AppnextAd a2 = b.R().a(g.this.context, (Ad) g.this.getBannerAd(), (ArrayList<?>) g.this.getAds(), g.this.getAdRequest().getCreativeType(), (ArrayList<String>) g.this.shown);
                        if (a2 != null) {
                            g.this.setSelectedAd(new BannerAdData(a2));
                            if (Boolean.parseBoolean(d.S().get("impOne"))) {
                                g.this.setReportedImpression(false);
                                g.this.impression();
                            } else if (Boolean.parseBoolean(d.S().get("pview"))) {
                                g.this.refreshHandler.postDelayed(new Runnable() {
                                    public final void run() {
                                        g.this.getUserAction().a(g.this.getSelectedAd(), g.this.getSelectedAd().getImpressionURL(), (e.a) null);
                                    }
                                }, (long) (Integer.parseInt(d.S().get("postpone_vta_sec")) * 1000));
                            }
                            g.this.pageFinished();
                        }
                    } catch (Throwable unused) {
                    }
                }
            }, (long) (i * 1000));
        }
    }

    public void impression() {
        boolean isReportedImpression = isReportedImpression();
        super.impression();
        if (isReportedImpression != isReportedImpression()) {
            activateAutoRefresh();
        }
    }
}
