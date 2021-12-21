package com.appsgeyser.sdk;

class ExceptionHandler {
    static void handleException(Exception exc) {
        Logger.ErrorLog(exc.getMessage());
    }
}
