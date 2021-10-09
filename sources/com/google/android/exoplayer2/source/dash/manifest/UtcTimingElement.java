package com.google.android.exoplayer2.source.dash.manifest;

public final class UtcTimingElement {
    public final String schemeIdUri;
    public final String value;

    public String toString() {
        return this.schemeIdUri + ", " + this.value;
    }
}
