package com.appsgeyser.multiTabApp.configuration;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializer<T> {
    private Context _context;

    public ObjectSerializer(Context context) {
        this._context = context;
    }

    public void serializeAndSaveObject(T t, String str) throws IOException {
        FileOutputStream openFileOutput = this._context.openFileOutput(str, 0);
        new ObjectOutputStream(openFileOutput).writeObject(t);
        openFileOutput.close();
    }

    public T loadSerializedObject(String str) throws IOException, ClassNotFoundException {
        if (this._context.getFileStreamPath(str).exists()) {
            return new ObjectInputStream(this._context.openFileInput(str)).readObject();
        }
        throw new IOException();
    }
}
