package com.appsgeyser.multiTabApp.configuration;

import android.content.Context;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.controllers.WidgetsController;
import com.appsgeyser.multiTabApp.utils.VersionManager;
import java.io.IOException;

public class WebWidgetConfigurationManager {
    private static WebWidgetConfigurationManager instance;
    private WebWidgetConfiguration config;
    private XMLConfigurationParser parser;

    public static WebWidgetConfigurationManager getInstance(Context context) {
        if (instance == null) {
            instance = new WebWidgetConfigurationManager(context);
        }
        return instance;
    }

    private WebWidgetConfigurationManager(Context context) {
        this.parser = new XMLConfigurationParser(context);
    }

    public WebWidgetConfiguration loadConfiguration(Context context) throws Exception {
        int currentVersion = VersionManager.getCurrentVersion(context);
        int previousVersion = VersionManager.getPreviousVersion(context);
        if (currentVersion != previousVersion || previousVersion == -1) {
            this.config = loadFromCurrentConfig(context);
        } else {
            try {
                this.config = loadSerializedConfiguration(context);
            } catch (Exception unused) {
                this.config = loadFromCurrentConfig(context);
            }
        }
        return this.config;
    }

    private WebWidgetConfiguration loadFromCurrentConfig(Context context) {
        try {
            WebWidgetConfiguration parse = this.parser.parse();
            saveConfiguration(parse, Factory.getInstance().getWidgetsController(), context);
            return parse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebWidgetConfiguration loadSerializedConfiguration(Context context) throws IOException, ClassNotFoundException {
        ObjectSerializer objectSerializer = new ObjectSerializer(context);
        Factory.getInstance().setWidgetsController((WidgetsController) new ObjectSerializer(context).loadSerializedObject("widgetsController"));
        return (WebWidgetConfiguration) objectSerializer.loadSerializedObject("webWidgetConfiguration");
    }

    public void saveConfiguration(final WebWidgetConfiguration webWidgetConfiguration, final WidgetsController widgetsController, final Context context) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ObjectSerializer objectSerializer = new ObjectSerializer(context);
                    ObjectSerializer objectSerializer2 = new ObjectSerializer(context);
                    objectSerializer.serializeAndSaveObject(webWidgetConfiguration, "webWidgetConfiguration");
                    objectSerializer2.serializeAndSaveObject(widgetsController, "widgetsController");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }
}
