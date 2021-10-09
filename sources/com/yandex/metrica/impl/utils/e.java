package com.yandex.metrica.impl.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Build;

public class e {
    public static void a(Cursor cursor, ContentValues contentValues) {
        if (Build.VERSION.SDK_INT >= 11) {
            String[] columnNames = cursor.getColumnNames();
            int length = columnNames.length;
            for (int i = 0; i < length; i++) {
                int type = cursor.getType(i);
                if (type == 0) {
                    contentValues.put(columnNames[i], cursor.getString(i));
                } else if (type == 1) {
                    contentValues.put(columnNames[i], Integer.valueOf(cursor.getInt(i)));
                } else if (type == 2) {
                    contentValues.put(columnNames[i], Float.valueOf(cursor.getFloat(i)));
                } else if (type == 3) {
                    contentValues.put(columnNames[i], cursor.getString(i));
                } else if (type != 4) {
                    contentValues.put(columnNames[i], cursor.getString(i));
                } else {
                    contentValues.put(columnNames[i], cursor.getBlob(i));
                }
            }
            return;
        }
        DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
    }

    public static String a(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("?,");
        }
        int length = sb.length();
        sb.replace(length - 1, length, "");
        return sb.toString();
    }
}
