package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Sequences.kt */
public final class TransformingSequence<T, R> implements Sequence<R> {
    /* access modifiers changed from: private */
    public final Sequence<T> sequence;
    /* access modifiers changed from: private */
    public final Function1<T, R> transformer;

    public TransformingSequence(Sequence<? extends T> sequence2, Function1<? super T, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(sequence2, "sequence");
        Intrinsics.checkParameterIsNotNull(function1, "transformer");
        this.sequence = sequence2;
        this.transformer = function1;
    }

    public Iterator<R> iterator() {
        return new TransformingSequence$iterator$1(this);
    }
}
