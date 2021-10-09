package com.appsgeyser.multiTabApp.suggestions;

import com.appnext.base.b.c;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SuggestionsHandler extends DefaultHandler {
    private final int CAPACITY_DEFAULT = 6;
    private final String XML_ATTRIBUTE_DATA = c.DATA;
    private final String XML_TAG_SUGGESTION = "suggestion";
    private int capacity = 6;
    private ArrayList<RemoteSuggestionItem> results = new ArrayList<>();

    public ArrayList<RemoteSuggestionItem> getResult() {
        return this.results;
    }

    public void startDocument() throws SAXException {
        super.startDocument();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String value;
        super.startElement(str, str2, str3, attributes);
        if (this.results.size() < this.capacity && str3 != null && attributes != null && str3.equals("suggestion") && (value = attributes.getValue(c.DATA)) != null) {
            this.results.add(new RemoteSuggestionItem(value));
        }
    }

    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
