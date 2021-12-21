package com.appsgeyser.multiTabApp.configuration;

import android.content.Context;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.controllers.WidgetsController;
import com.wGhostlySkills_14510784.R;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;

public class XMLConfigurationParser {
    private Context context;

    public XMLConfigurationParser(Context context2) {
        this.context = context2;
    }

    public WebWidgetConfiguration parse() throws Exception {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        try {
            WidgetsController widgetsController = Factory.getInstance().getWidgetsController();
            WebWidgetConfiguration webWidgetConfiguration = new WebWidgetConfiguration();
            widgetsController.removeAll();
            newInstance.newSAXParser().parse(getConfigurationStream(), new XMLConfigurationHandler(webWidgetConfiguration, widgetsController));
            return webWidgetConfiguration;
        } catch (Exception unused) {
            throw new Exception("Wrong format of configuration.xml file");
        }
    }

    private InputStream getConfigurationStream() throws Exception {
        try {
            return this.context.getResources().openRawResource(R.raw.configuration);
        } catch (Exception unused) {
            throw new Exception("Couldn't find configuration file");
        }
    }
}
