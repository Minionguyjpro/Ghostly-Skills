package com.yandex.metrica.impl;

import android.content.Context;
import android.util.Base64;
import com.yandex.metrica.YandexMetrica;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class r {
    public static String a(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        StringWriter stringWriter = new StringWriter();
        a((Reader) inputStreamReader, (Writer) stringWriter);
        return stringWriter.toString();
    }

    public static String a(String str) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                String a2 = a((InputStream) fileInputStream2);
                bk.a((Closeable) fileInputStream2);
                return a2;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                bk.a((Closeable) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bk.a((Closeable) fileInputStream);
            throw th;
        }
    }

    public static int a(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[4096];
        int i = 0;
        while (true) {
            int read = reader.read(cArr, 0, 4096);
            if (-1 == read) {
                return i;
            }
            writer.write(cArr, 0, read);
            i += read;
        }
    }

    public static String b(String str) {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Exception unused) {
                gZIPOutputStream = null;
                bk.a((Closeable) gZIPOutputStream);
                bk.a((Closeable) byteArrayOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                bk.a((Closeable) gZIPOutputStream2);
                bk.a((Closeable) byteArrayOutputStream);
                throw th;
            }
            try {
                gZIPOutputStream.write(str.getBytes("UTF-8"));
                gZIPOutputStream.finish();
                String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                bk.a((Closeable) gZIPOutputStream);
                bk.a((Closeable) byteArrayOutputStream);
                return encodeToString;
            } catch (Exception unused2) {
                bk.a((Closeable) gZIPOutputStream);
                bk.a((Closeable) byteArrayOutputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream2 = gZIPOutputStream;
                bk.a((Closeable) gZIPOutputStream2);
                bk.a((Closeable) byteArrayOutputStream);
                throw th;
            }
        } catch (Exception unused3) {
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
            bk.a((Closeable) gZIPOutputStream);
            bk.a((Closeable) byteArrayOutputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            bk.a((Closeable) gZIPOutputStream2);
            bk.a((Closeable) byteArrayOutputStream);
            throw th;
        }
    }

    public static String c(String str) {
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        String str2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } catch (Exception unused) {
                gZIPInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream = null;
                bk.a((Closeable) byteArrayInputStream);
                bk.a((Closeable) gZIPInputStream);
                throw th;
            }
            try {
                str2 = a((InputStream) gZIPInputStream);
            } catch (Exception unused2) {
            } catch (Throwable th3) {
                th = th3;
                bk.a((Closeable) byteArrayInputStream);
                bk.a((Closeable) gZIPInputStream);
                throw th;
            }
        } catch (Exception unused3) {
            gZIPInputStream = null;
            byteArrayInputStream = null;
        } catch (Throwable th4) {
            byteArrayInputStream = null;
            th = th4;
            gZIPInputStream = null;
            bk.a((Closeable) byteArrayInputStream);
            bk.a((Closeable) gZIPInputStream);
            throw th;
        }
        bk.a((Closeable) byteArrayInputStream);
        bk.a((Closeable) gZIPInputStream);
        return str2;
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return byteArrayOutputStream.toByteArray();
                }
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } finally {
                bk.a((Closeable) byteArrayOutputStream);
            }
        }
    }

    public static String a(Context context, File file) {
        byte[] b = b(context, file);
        try {
            return new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            String str = new String(b);
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("read_share_file_with_unsupported_encoding", e);
            return str;
        }
    }

    public static byte[] b(Context context, File file) {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                fileLock = channel.lock(0, Long.MAX_VALUE, true);
                try {
                    ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
                    channel.read(allocate);
                    allocate.flip();
                    byte[] array = allocate.array();
                    file.getAbsolutePath();
                    a(fileLock);
                    bk.a((Closeable) randomAccessFile);
                    return array;
                } catch (IOException | SecurityException unused) {
                    file.getAbsolutePath();
                    a(fileLock);
                    bk.a((Closeable) randomAccessFile);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    try {
                        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("error_during_file_reading", th);
                        file.getAbsolutePath();
                        a(fileLock);
                        bk.a((Closeable) randomAccessFile);
                        return null;
                    } catch (Throwable th2) {
                        file.getAbsolutePath();
                        a(fileLock);
                        bk.a((Closeable) randomAccessFile);
                        throw th2;
                    }
                }
            } catch (IOException | SecurityException unused2) {
                fileLock = null;
                file.getAbsolutePath();
                a(fileLock);
                bk.a((Closeable) randomAccessFile);
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileLock = null;
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("error_during_file_reading", th);
                file.getAbsolutePath();
                a(fileLock);
                bk.a((Closeable) randomAccessFile);
                return null;
            }
        } catch (IOException | SecurityException unused3) {
            randomAccessFile = null;
            fileLock = null;
            file.getAbsolutePath();
            a(fileLock);
            bk.a((Closeable) randomAccessFile);
            return null;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = null;
            fileLock = null;
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("error_during_file_reading", th);
            file.getAbsolutePath();
            a(fileLock);
            bk.a((Closeable) randomAccessFile);
            return null;
        }
    }

    public static void a(FileLock fileLock) {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException unused) {
            }
        }
    }

    public static void b(Context context, String str, String str2) {
        File file = new File(context.getNoBackupFilesDir(), str);
        try {
            a(str2, new FileOutputStream(file));
            c(context, file);
        } catch (FileNotFoundException unused) {
        }
    }

    public static void c(final Context context, final File file) {
        if (file.exists()) {
            file.setReadable(true, false);
            if (bk.a(24)) {
                new File(context.getApplicationInfo().dataDir).setExecutable(true, false);
                return;
            }
            return;
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("make_non_existed_world_readable", new HashMap<String, Object>() {
            {
                put("file_name", file.getName());
                put("applicationId", context.getPackageName());
            }
        });
    }

    private static void a(String str, FileOutputStream fileOutputStream) {
        FileLock fileLock = null;
        try {
            FileChannel channel = fileOutputStream.getChannel();
            fileLock = channel.lock();
            byte[] bytes = str.getBytes("UTF-8");
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
            allocate.put(bytes);
            allocate.flip();
            channel.write(allocate);
            channel.force(true);
        } catch (IOException unused) {
        } catch (Throwable th) {
            a(fileLock);
            bk.a((Closeable) fileOutputStream);
            throw th;
        }
        a(fileLock);
        bk.a((Closeable) fileOutputStream);
    }

    public static void a(Context context, String str, String str2) {
        try {
            if (bk.a(24)) {
                a(str2, context.openFileOutput(str, 0));
                c(context, context.getFileStreamPath(str));
                return;
            }
            a(str2, context.openFileOutput(str, 1));
        } catch (FileNotFoundException unused) {
        }
    }
}
