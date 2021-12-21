package com.truenet.android;

import a.a.b.b.h;
import com.startapp.common.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class ValidationResults {
    @f(b = ArrayList.class, c = ValidationResult.class)
    private final List<ValidationResult> results;

    public static /* synthetic */ ValidationResults copy$default(ValidationResults validationResults, List<ValidationResult> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = validationResults.results;
        }
        return validationResults.copy(list);
    }

    public final List<ValidationResult> component1() {
        return this.results;
    }

    public final ValidationResults copy(List<ValidationResult> list) {
        h.b(list, "results");
        return new ValidationResults(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ValidationResults) && h.a((Object) this.results, (Object) ((ValidationResults) obj).results);
        }
        return true;
    }

    public int hashCode() {
        List<ValidationResult> list = this.results;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ValidationResults(results=" + this.results + ")";
    }

    public ValidationResults(List<ValidationResult> list) {
        h.b(list, "results");
        this.results = list;
    }

    public final List<ValidationResult> getResults() {
        return this.results;
    }
}
