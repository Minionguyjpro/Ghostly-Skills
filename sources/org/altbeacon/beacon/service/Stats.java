package org.altbeacon.beacon.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.logging.LogManager;

public class Stats {
    private static final Stats INSTANCE = new Stats();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    private boolean mEnableHistoricalLogging;
    private boolean mEnableLogging;
    private boolean mEnabled;
    private Sample mSample;
    private long mSampleIntervalMillis = 0;
    private ArrayList<Sample> mSamples;

    public static class Sample {
        public long detectionCount = 0;
        public Date firstDetectionTime;
        public Date lastDetectionTime;
        public long maxMillisBetweenDetections;
        public Date sampleStartTime;
        public Date sampleStopTime;
    }

    public static Stats getInstance() {
        return INSTANCE;
    }

    private Stats() {
        clearSamples();
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void log(Beacon beacon) {
        rollSampleIfNeeded();
        this.mSample.detectionCount++;
        if (this.mSample.firstDetectionTime == null) {
            this.mSample.firstDetectionTime = new Date();
        }
        if (this.mSample.lastDetectionTime != null) {
            long time = new Date().getTime() - this.mSample.lastDetectionTime.getTime();
            if (time > this.mSample.maxMillisBetweenDetections) {
                this.mSample.maxMillisBetweenDetections = time;
            }
        }
        this.mSample.lastDetectionTime = new Date();
    }

    public void newSampleInterval() {
        Date date = new Date();
        if (this.mSample != null) {
            date = new Date(this.mSample.sampleStartTime.getTime() + this.mSampleIntervalMillis);
            this.mSample.sampleStopTime = date;
            if (!this.mEnableHistoricalLogging && this.mEnableLogging) {
                logSample(this.mSample, true);
            }
        }
        Sample sample = new Sample();
        this.mSample = sample;
        sample.sampleStartTime = date;
        this.mSamples.add(this.mSample);
        if (this.mEnableHistoricalLogging) {
            logSamples();
        }
    }

    public void clearSamples() {
        this.mSamples = new ArrayList<>();
        newSampleInterval();
    }

    private void logSample(Sample sample, boolean z) {
        if (z) {
            LogManager.d("Stats", "sample start time, sample stop time, first detection time, last detection time, max millis between detections, detection count", new Object[0]);
        }
        LogManager.d("Stats", "%s, %s, %s, %s, %s, %s", formattedDate(sample.sampleStartTime), formattedDate(sample.sampleStopTime), formattedDate(sample.firstDetectionTime), formattedDate(sample.lastDetectionTime), Long.valueOf(sample.maxMillisBetweenDetections), Long.valueOf(sample.detectionCount));
    }

    private String formattedDate(Date date) {
        String str = "";
        if (date != null) {
            synchronized (SIMPLE_DATE_FORMAT) {
                str = SIMPLE_DATE_FORMAT.format(date);
            }
        }
        return str;
    }

    private void logSamples() {
        boolean z = true;
        LogManager.d("Stats", "--- Stats for %s samples", Integer.valueOf(this.mSamples.size()));
        Iterator<Sample> it = this.mSamples.iterator();
        while (it.hasNext()) {
            logSample(it.next(), z);
            z = false;
        }
    }

    private void rollSampleIfNeeded() {
        if (this.mSample == null || (this.mSampleIntervalMillis > 0 && new Date().getTime() - this.mSample.sampleStartTime.getTime() >= this.mSampleIntervalMillis)) {
            newSampleInterval();
        }
    }
}
