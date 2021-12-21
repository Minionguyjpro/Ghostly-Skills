package com.appsgeyser.multiTabApp.suggestions;

import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.server.BaseServerClient;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

public class SuggestionsClient extends BaseServerClient {
    /* access modifiers changed from: private */
    public SuggestionsListener listener;

    public SuggestionsClient(MainNavigationActivity mainNavigationActivity) {
        super(mainNavigationActivity);
    }

    public void setListener(SuggestionsListener suggestionsListener) {
        this.listener = suggestionsListener;
    }

    public void getSuggestionsAsync(final String str) {
        if (str != null && str != "") {
            sendRequestAsync("http://google.com/complete/search?output=toolbar&q=" + URLEncoder.encode(str), 99999, new BaseServerClient.OnRequestDoneListener() {
                public void onRequestDone(String str, int i, String str2) {
                    ArrayList arrayList;
                    if (i == 99999 && str2 != null) {
                        try {
                            arrayList = SuggestionsClient.this.tryGetSuggestsFromXml(str2);
                        } catch (Exception unused) {
                            arrayList = new ArrayList();
                        }
                        SuggestionsClient.this.listener.onReceiveSuggestions(arrayList, str);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public ArrayList<RemoteSuggestionItem> tryGetSuggestsFromXml(String str) {
        try {
            SAXParser newSAXParser = SAXParserFactory.newInstance().newSAXParser();
            SuggestionsHandler suggestionsHandler = new SuggestionsHandler();
            newSAXParser.parse(new InputSource(new StringReader(str)), suggestionsHandler);
            return suggestionsHandler.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
