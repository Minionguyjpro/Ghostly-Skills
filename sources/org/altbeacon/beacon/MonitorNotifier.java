package org.altbeacon.beacon;

public interface MonitorNotifier {
    void didDetermineStateForRegion(int i, Region region);

    void didEnterRegion(Region region);

    void didExitRegion(Region region);
}
