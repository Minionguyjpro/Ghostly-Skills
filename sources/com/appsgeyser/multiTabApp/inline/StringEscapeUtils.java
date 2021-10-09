package com.appsgeyser.multiTabApp.inline;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

public class StringEscapeUtils {
    private static final String CSV_QUOTE_STR = String.valueOf('\"');

    public static String escapeJavaScript(String str) {
        try {
            return escapeJavaStyleString(str, true, true);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String escapeJavaStyleString(String str, boolean z, boolean z2) throws Exception {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(str.length() * 2);
            escapeJavaStyleString(stringWriter, str, z, z2);
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new Exception("escapeJavaStyleString error!");
        }
    }

    private static void escapeJavaStyleString(Writer writer, String str, boolean z, boolean z2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt > 4095) {
                    writer.write("\\u" + hex(charAt));
                } else if (charAt > 255) {
                    writer.write("\\u0" + hex(charAt));
                } else if (charAt > 127) {
                    writer.write("\\u00" + hex(charAt));
                } else if (charAt < ' ') {
                    switch (charAt) {
                        case 8:
                            writer.write(92);
                            writer.write(98);
                            break;
                        case 9:
                            writer.write(92);
                            writer.write(116);
                            break;
                        case 10:
                            writer.write(92);
                            writer.write(110);
                            break;
                        case 12:
                            writer.write(92);
                            writer.write(102);
                            break;
                        case 13:
                            writer.write(92);
                            writer.write(114);
                            break;
                        default:
                            if (charAt <= 15) {
                                writer.write("\\u000" + hex(charAt));
                                break;
                            } else {
                                writer.write("\\u00" + hex(charAt));
                                break;
                            }
                    }
                } else if (charAt == '\"') {
                    writer.write(92);
                    writer.write(34);
                } else if (charAt == '\'') {
                    if (z) {
                        writer.write(92);
                    }
                    writer.write(39);
                } else if (charAt == '/') {
                    if (z2) {
                        writer.write(92);
                    }
                    writer.write(47);
                } else if (charAt != '\\') {
                    writer.write(charAt);
                } else {
                    writer.write(92);
                    writer.write(92);
                }
            }
        }
    }

    private static String hex(char c) {
        return Integer.toHexString(c).toUpperCase(Locale.ENGLISH);
    }
}
