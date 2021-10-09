package com.startapp.common.a;

import android.content.Context;
import com.startapp.common.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class e {
    public static <T> T a(Context context, String str, Class<T> cls) {
        return a(context, (String) null, str, cls);
    }

    public static void a(Context context, String str, Serializable serializable) {
        a(context, (String) null, str, serializable);
    }

    public static void b(final Context context, final String str, final Serializable serializable) {
        g.a(g.a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                e.a(context, (String) null, str, serializable);
            }
        });
    }

    public static void a(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            g.a("FileUtils", 3, "writeToDisk: fileName is null");
            return;
        }
        try {
            a(b(context, str), str2, serializable);
        } catch (Exception e) {
            g.a("FileUtils", 3, "Failed writing to disk: " + e.getLocalizedMessage());
        }
    }

    public static void b(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            g.a("FileUtils", 3, "writeToDisk: fileName is null");
            return;
        }
        try {
            a(c(context, str), str2, serializable);
        } catch (Exception e) {
            g.a("FileUtils", 3, "Failed writing to disk: " + e.getLocalizedMessage());
        }
    }

    public static <T> T a(Context context, String str, String str2, Class<T> cls) {
        if (str2 == null) {
            g.a(3, "readFromDisk::fileName is null");
            return null;
        }
        try {
            return a(b(context, str), str2);
        } catch (Exception e) {
            g.a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Error e2) {
            g.a("FileUtils", 6, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T b(Context context, String str, String str2, Class<T> cls) {
        if (str2 == null) {
            g.a(3, "readFromDisk::fileName is null");
            return null;
        }
        try {
            return a(c(context, str), str2);
        } catch (Exception e) {
            g.a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Error e2) {
            g.a("FileUtils", 6, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    public static <T> List<T> b(Context context, String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(c(context, str));
            if (file.exists()) {
                if (file.isDirectory()) {
                    String[] list = file.list();
                    if (list == null) {
                        g.a("FileUtils", 3, "Files directory is empty");
                        return null;
                    }
                    for (String file2 : list) {
                        File file3 = new File(file, file2);
                        g.a("FileUtils", 4, "Reading file: " + file3.getPath());
                        arrayList.add(b(file3));
                    }
                    return arrayList;
                }
            }
            g.a("FileUtils", 3, "Files directory does not exist or not a directory: " + str);
            return null;
        } catch (Exception e) {
            g.a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
        }
    }

    public static void a(Context context, String str) {
        if (str == null) {
            g.a(3, "deleteDirectory::dirPath == null");
            return;
        }
        a(new File(b(context, str)));
        a(new File(c(context, str)));
    }

    private static String b(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    private static String c(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    private static void a(String str, String str2, Serializable serializable) {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, str2);
            g.a("FileUtils", 4, "Writing file: " + file2.getPath());
            a(serializable, file2);
            return;
        }
        g.a("FileUtils", 3, "Unable to create directories");
    }

    private static <T> T a(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            g.a("FileUtils", 3, "Files directory does not exist or not a directory");
            return null;
        }
        File file2 = new File(file, str2);
        if (!file2.exists()) {
            return null;
        }
        g.a("FileUtils", 4, "Reading file: " + file2.getPath());
        return b(file2);
    }

    private static void a(File file) {
        if (file.isDirectory()) {
            for (File a2 : file.listFiles()) {
                a(a2);
            }
        }
        file.delete();
    }

    private static void a(Serializable serializable, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private static <T> T b(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T readObject = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return readObject;
    }
}
