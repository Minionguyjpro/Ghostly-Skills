package com.google.android.exoplayer2.database;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public final class VersionTable {
    public static void setVersion(SQLiteDatabase sQLiteDatabase, int i, String str, int i2) throws DatabaseIOException {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ExoPlayerVersions (feature INTEGER NOT NULL,instance_uid TEXT NOT NULL,version INTEGER NOT NULL,PRIMARY KEY (feature, instance_uid))");
            ContentValues contentValues = new ContentValues();
            contentValues.put("feature", Integer.valueOf(i));
            contentValues.put("instance_uid", str);
            contentValues.put("version", Integer.valueOf(i2));
            sQLiteDatabase.replaceOrThrow("ExoPlayerVersions", (String) null, contentValues);
        } catch (SQLException e) {
            throw new DatabaseIOException(e);
        }
    }

    public static void removeVersion(SQLiteDatabase sQLiteDatabase, int i, String str) throws DatabaseIOException {
        try {
            if (tableExists(sQLiteDatabase, "ExoPlayerVersions")) {
                sQLiteDatabase.delete("ExoPlayerVersions", "feature = ? AND instance_uid = ?", featureAndInstanceUidArguments(i, str));
            }
        } catch (SQLException e) {
            throw new DatabaseIOException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003d, code lost:
        if (r10 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getVersion(android.database.sqlite.SQLiteDatabase r10, int r11, java.lang.String r12) throws com.google.android.exoplayer2.database.DatabaseIOException {
        /*
            java.lang.String r0 = "ExoPlayerVersions"
            boolean r0 = tableExists(r10, r0)     // Catch:{ SQLException -> 0x0048 }
            r1 = -1
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            java.lang.String r3 = "ExoPlayerVersions"
            java.lang.String r0 = "version"
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch:{ SQLException -> 0x0048 }
            java.lang.String r5 = "feature = ? AND instance_uid = ?"
            java.lang.String[] r6 = featureAndInstanceUidArguments(r11, r12)     // Catch:{ SQLException -> 0x0048 }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLException -> 0x0048 }
            int r11 = r10.getCount()     // Catch:{ all -> 0x003a }
            if (r11 != 0) goto L_0x002c
            if (r10 == 0) goto L_0x002b
            r10.close()     // Catch:{ SQLException -> 0x0048 }
        L_0x002b:
            return r1
        L_0x002c:
            r10.moveToNext()     // Catch:{ all -> 0x003a }
            r11 = 0
            int r11 = r10.getInt(r11)     // Catch:{ all -> 0x003a }
            if (r10 == 0) goto L_0x0039
            r10.close()     // Catch:{ SQLException -> 0x0048 }
        L_0x0039:
            return r11
        L_0x003a:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x003c }
        L_0x003c:
            r12 = move-exception
            if (r10 == 0) goto L_0x0047
            r10.close()     // Catch:{ all -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r10 = move-exception
            r11.addSuppressed(r10)     // Catch:{ SQLException -> 0x0048 }
        L_0x0047:
            throw r12     // Catch:{ SQLException -> 0x0048 }
        L_0x0048:
            r10 = move-exception
            com.google.android.exoplayer2.database.DatabaseIOException r11 = new com.google.android.exoplayer2.database.DatabaseIOException
            r11.<init>(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.database.VersionTable.getVersion(android.database.sqlite.SQLiteDatabase, int, java.lang.String):int");
    }

    static boolean tableExists(SQLiteDatabase sQLiteDatabase, String str) {
        return DatabaseUtils.queryNumEntries(sQLiteDatabase, "sqlite_master", "tbl_name = ?", new String[]{str}) > 0;
    }

    private static String[] featureAndInstanceUidArguments(int i, String str) {
        return new String[]{Integer.toString(i), str};
    }
}
