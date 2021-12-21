package com.google.android.youtube.player;

import android.content.Context;
import android.content.Intent;
import com.google.android.youtube.player.internal.z;
import com.mopub.network.ImpressionData;

public final class YouTubeIntents {
    static Intent a(Intent intent, Context context) {
        intent.putExtra("app_package", context.getPackageName()).putExtra(ImpressionData.APP_VERSION, z.d(context)).putExtra("client_library_version", z.a());
        return intent;
    }
}
