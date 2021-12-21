package androidx.media2.exoplayer.external.upstream;

public interface Allocator {
    Allocation allocate();

    int getIndividualAllocationLength();

    void release(Allocation allocation);

    void release(Allocation[] allocationArr);

    void trim();
}
