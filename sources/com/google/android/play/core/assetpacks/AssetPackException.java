package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.a;
import com.google.android.play.core.tasks.j;

public class AssetPackException extends j {

    /* renamed from: a  reason: collision with root package name */
    private final int f1019a;

    AssetPackException(int i) {
        super(String.format("Asset Pack Download Error(%d): %s", new Object[]{Integer.valueOf(i), a.a(i)}));
        if (i != 0) {
            this.f1019a = i;
            return;
        }
        throw new IllegalArgumentException("errorCode should not be 0.");
    }
}
