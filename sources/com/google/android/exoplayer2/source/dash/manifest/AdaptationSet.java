package com.google.android.exoplayer2.source.dash.manifest;

import java.util.List;

public class AdaptationSet {
    public final List<Descriptor> accessibilityDescriptors;
    public final int id;
    public final List<Representation> representations;
    public final List<Descriptor> supplementalProperties;
    public final int type;
}
