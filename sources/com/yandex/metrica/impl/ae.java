package com.yandex.metrica.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

public interface ae {
    void a();

    void a(int i, Bundle bundle) throws RemoteException;

    void a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException;

    void a(Intent intent);

    void a(Intent intent, int i);

    void a(Intent intent, int i, int i2);

    void b();

    void b(Intent intent);

    void c(Intent intent);
}
