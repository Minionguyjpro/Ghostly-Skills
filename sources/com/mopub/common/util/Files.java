package com.mopub.common.util;

import java.io.File;

public class Files {
    public static File createDirectory(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if ((!file.exists() || !file.isDirectory()) && (!file.mkdirs() || !file.isDirectory())) {
            return null;
        }
        return file;
    }

    public static int intLength(File file) {
        if (file == null) {
            return 0;
        }
        long length = file.length();
        if (length < 2147483647L) {
            return (int) length;
        }
        return Integer.MAX_VALUE;
    }
}
