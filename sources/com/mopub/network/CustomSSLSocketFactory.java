package com.mopub.network;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Build;
import com.mopub.common.Preconditions;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLSocketFactory extends SSLSocketFactory {
    private SSLCertificateSocketFactory mCertificateSocketFactory;

    private CustomSSLSocketFactory() {
    }

    public static CustomSSLSocketFactory getDefault(int i) {
        CustomSSLSocketFactory customSSLSocketFactory = new CustomSSLSocketFactory();
        customSSLSocketFactory.mCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(i, (SSLSessionCache) null);
        return customSSLSocketFactory;
    }

    public Socket createSocket() throws IOException {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory != null) {
            Socket createSocket = sSLCertificateSocketFactory.createSocket();
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory != null) {
            Socket createSocket = sSLCertificateSocketFactory.createSocket(str, i);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory != null) {
            Socket createSocket = sSLCertificateSocketFactory.createSocket(str, i, inetAddress, i2);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory != null) {
            Socket createSocket = sSLCertificateSocketFactory.createSocket(inetAddress, i);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory != null) {
            Socket createSocket = sSLCertificateSocketFactory.createSocket(inetAddress, i, inetAddress2, i2);
            enableTlsIfAvailable(createSocket);
            return createSocket;
        }
        throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
    }

    public String[] getDefaultCipherSuites() {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory == null) {
            return new String[0];
        }
        return sSLCertificateSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory == null) {
            return new String[0];
        }
        return sSLCertificateSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.mCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (Build.VERSION.SDK_INT < 23) {
            if (z && socket != null) {
                socket.close();
            }
            Socket createSocket = this.mCertificateSocketFactory.createSocket(InetAddressUtils.getInetAddressByName(str), i);
            enableTlsIfAvailable(createSocket);
            doManualServerNameIdentification(createSocket, str);
            return createSocket;
        } else {
            Socket createSocket2 = this.mCertificateSocketFactory.createSocket(socket, str, i, z);
            enableTlsIfAvailable(createSocket2);
            return createSocket2;
        }
    }

    private void doManualServerNameIdentification(Socket socket, String str) throws IOException {
        Preconditions.checkNotNull(socket);
        SSLCertificateSocketFactory sSLCertificateSocketFactory = this.mCertificateSocketFactory;
        if (sSLCertificateSocketFactory == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        } else if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
            verifyServerName(sSLSocket, str);
        }
    }

    static void verifyServerName(SSLSocket sSLSocket, String str) throws IOException {
        Preconditions.checkNotNull(sSLSocket);
        sSLSocket.startHandshake();
        if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSocket.getSession())) {
            throw new SSLHandshakeException("Server Name Identification failed.");
        }
    }

    private void enableTlsIfAvailable(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setCertificateSocketFactory(SSLCertificateSocketFactory sSLCertificateSocketFactory) {
        this.mCertificateSocketFactory = sSLCertificateSocketFactory;
    }
}
