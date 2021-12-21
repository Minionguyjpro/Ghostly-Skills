package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzks extends IInterface {
    void destroy() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzlo getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzaaw zzaaw) throws RemoteException;

    void zza(zzabc zzabc, String str) throws RemoteException;

    void zza(zzahe zzahe) throws RemoteException;

    void zza(zzjn zzjn) throws RemoteException;

    void zza(zzke zzke) throws RemoteException;

    void zza(zzkh zzkh) throws RemoteException;

    void zza(zzkx zzkx) throws RemoteException;

    void zza(zzla zzla) throws RemoteException;

    void zza(zzlg zzlg) throws RemoteException;

    void zza(zzlu zzlu) throws RemoteException;

    void zza(zzmu zzmu) throws RemoteException;

    void zza(zzod zzod) throws RemoteException;

    boolean zzb(zzjj zzjj) throws RemoteException;

    Bundle zzba() throws RemoteException;

    IObjectWrapper zzbj() throws RemoteException;

    zzjn zzbk() throws RemoteException;

    void zzbm() throws RemoteException;

    zzla zzbw() throws RemoteException;

    zzkh zzbx() throws RemoteException;

    String zzck() throws RemoteException;
}
