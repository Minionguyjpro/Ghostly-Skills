package com.appsgeyser.multiTabApp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;
import java.util.Locale;

public class TimePreference extends DialogPreference {
    private int lastHour = 0;
    private int lastMinute = 0;
    private TimePicker picker = null;

    public static int getHour(String str) {
        return Integer.parseInt(str.split(":")[0]);
    }

    public static int getMinute(String str) {
        return Integer.parseInt(str.split(":")[1]);
    }

    public TimePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }

    /* access modifiers changed from: protected */
    public View onCreateDialogView() {
        TimePicker timePicker = new TimePicker(getContext());
        this.picker = timePicker;
        return timePicker;
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.picker.setCurrentHour(Integer.valueOf(this.lastHour));
        this.picker.setCurrentMinute(Integer.valueOf(this.lastMinute));
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (z) {
            this.lastHour = this.picker.getCurrentHour().intValue();
            this.lastMinute = this.picker.getCurrentMinute().intValue();
            String str = String.format(Locale.US, "%02d", new Object[]{Integer.valueOf(this.lastHour)}) + ":" + String.format(Locale.US, "%02d", new Object[]{Integer.valueOf(this.lastMinute)});
            if (callChangeListener(str)) {
                persistString(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        String str;
        if (!z) {
            str = obj.toString();
        } else if (obj == null) {
            str = getPersistedString("00:00");
        } else {
            str = getPersistedString(obj.toString());
        }
        this.lastHour = getHour(str);
        this.lastMinute = getMinute(str);
    }
}
