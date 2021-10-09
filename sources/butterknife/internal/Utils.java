package butterknife.internal;

import android.util.TypedValue;
import android.view.View;

public final class Utils {
    private static final TypedValue VALUE = new TypedValue();

    public static View findRequiredView(View view, int i, String str) {
        View findViewById = view.findViewById(i);
        if (findViewById != null) {
            return findViewById;
        }
        String resourceEntryName = getResourceEntryName(view, i);
        throw new IllegalStateException("Required view '" + resourceEntryName + "' with ID " + i + " for " + str + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    }

    public static <T> T findRequiredViewAsType(View view, int i, String str, Class<T> cls) {
        return castView(findRequiredView(view, i, str), i, str, cls);
    }

    public static <T> T castView(View view, int i, String str, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (ClassCastException e) {
            String resourceEntryName = getResourceEntryName(view, i);
            throw new IllegalStateException("View '" + resourceEntryName + "' with ID " + i + " for " + str + " was of the wrong type. See cause for more info.", e);
        }
    }

    private static String getResourceEntryName(View view, int i) {
        if (view.isInEditMode()) {
            return "<unavailable while editing>";
        }
        return view.getContext().getResources().getResourceEntryName(i);
    }
}
