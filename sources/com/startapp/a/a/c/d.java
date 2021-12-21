package com.startapp.a.a.c;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/* compiled from: StartAppSDK */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final char f10a = File.separatorChar;
    public static final String b;

    static {
        e eVar = new e(4);
        PrintWriter printWriter = new PrintWriter(eVar);
        printWriter.println();
        b = eVar.toString();
        printWriter.close();
    }

    public static void a(OutputStream outputStream) {
        a((Closeable) outputStream);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
