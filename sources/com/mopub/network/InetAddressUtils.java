package com.mopub.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtils {
    public static InetAddress getInetAddressByName(String str) throws UnknownHostException {
        return InetAddress.getByName(str);
    }

    private InetAddressUtils() {
    }
}
