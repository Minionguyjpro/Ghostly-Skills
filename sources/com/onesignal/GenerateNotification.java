package com.onesignal;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.AdType;
import com.onesignal.AndroidSupportV4Compat;
import com.onesignal.OneSignal;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GenerateNotification {
    private static Resources contextResources;
    private static Context currentContext;
    private static Class<?> notificationOpenedClass;
    private static boolean openerIsBroadcast;
    private static String packageName;

    private static int convertOSToAndroidPriority(int i) {
        if (i > 9) {
            return 2;
        }
        if (i > 7) {
            return 1;
        }
        if (i > 4) {
            return 0;
        }
        return i > 2 ? -1 : -2;
    }

    private static class OneSignalNotificationBuilder {
        NotificationCompat.Builder compatBuilder;
        boolean hasLargeIcon;

        private OneSignalNotificationBuilder() {
        }
    }

    private static void setStatics(Context context) {
        currentContext = context;
        packageName = context.getPackageName();
        contextResources = currentContext.getResources();
        PackageManager packageManager = currentContext.getPackageManager();
        Intent intent = new Intent(currentContext, NotificationOpenedReceiver.class);
        intent.setPackage(currentContext.getPackageName());
        if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
            openerIsBroadcast = true;
            notificationOpenedClass = NotificationOpenedReceiver.class;
            return;
        }
        notificationOpenedClass = NotificationOpenedActivity.class;
    }

    static void fromJsonPayload(NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        if (notificationGenerationJob.restoring || !notificationGenerationJob.showAsAlert || ActivityLifecycleHandler.curActivity == null) {
            showNotification(notificationGenerationJob);
        } else {
            showNotificationAsAlert(notificationGenerationJob.jsonPayload, ActivityLifecycleHandler.curActivity, notificationGenerationJob.getAndroidId().intValue());
        }
    }

    private static void showNotificationAsAlert(final JSONObject jSONObject, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(GenerateNotification.getTitle(jSONObject));
                builder.setMessage(jSONObject.optString("alert"));
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                GenerateNotification.addAlertButtons(activity, jSONObject, arrayList, arrayList2);
                final Intent access$200 = GenerateNotification.getNewBaseIntent(i);
                access$200.putExtra("action_button", true);
                access$200.putExtra("from_alert", true);
                access$200.putExtra("onesignalData", jSONObject.toString());
                if (jSONObject.has("grp")) {
                    access$200.putExtra("grp", jSONObject.optString("grp"));
                }
                AnonymousClass1 r4 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int i2 = i + 3;
                        if (arrayList2.size() > 1) {
                            try {
                                JSONObject jSONObject = new JSONObject(jSONObject.toString());
                                jSONObject.put("actionId", arrayList2.get(i2));
                                access$200.putExtra("onesignalData", jSONObject.toString());
                                NotificationOpenedProcessor.processIntent(activity, access$200);
                            } catch (Throwable unused) {
                            }
                        } else {
                            NotificationOpenedProcessor.processIntent(activity, access$200);
                        }
                    }
                };
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        NotificationOpenedProcessor.processIntent(activity, access$200);
                    }
                });
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == 0) {
                        builder.setNeutralButton((CharSequence) arrayList.get(i), r4);
                    } else if (i == 1) {
                        builder.setNegativeButton((CharSequence) arrayList.get(i), r4);
                    } else if (i == 2) {
                        builder.setPositiveButton((CharSequence) arrayList.get(i), r4);
                    }
                }
                AlertDialog create = builder.create();
                create.setCanceledOnTouchOutside(false);
                create.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public static CharSequence getTitle(JSONObject jSONObject) {
        String optString = jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, (String) null);
        if (optString != null) {
            return optString;
        }
        return currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
    }

    private static PendingIntent getNewActionPendingIntent(int i, Intent intent) {
        if (openerIsBroadcast) {
            return PendingIntent.getBroadcast(currentContext, i, intent, 134217728);
        }
        return PendingIntent.getActivity(currentContext, i, intent, 134217728);
    }

    /* access modifiers changed from: private */
    public static Intent getNewBaseIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("androidNotificationId", i);
        if (openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(603979776);
    }

    private static Intent getNewBaseDeleteIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("androidNotificationId", i).putExtra("dismissed", true);
        if (openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(402718720);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|5|(1:9)|10|11|(1:13)|14|15|(1:17)(1:18)|19|21|(1:23)|24|(1:26)|27|(2:29|30)|31|33) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x006c */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0072 A[Catch:{ all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007b A[Catch:{ all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac A[SYNTHETIC, Splitter:B:29:0x00ac] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.onesignal.GenerateNotification.OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(com.onesignal.NotificationGenerationJob r9) {
        /*
            java.lang.String r0 = "vis"
            org.json.JSONObject r1 = r9.jsonPayload
            com.onesignal.GenerateNotification$OneSignalNotificationBuilder r2 = new com.onesignal.GenerateNotification$OneSignalNotificationBuilder
            r3 = 0
            r2.<init>()
            java.lang.String r4 = com.onesignal.NotificationChannelManager.createNotificationChannel(r9)     // Catch:{ all -> 0x0016 }
            androidx.core.app.NotificationCompat$Builder r5 = new androidx.core.app.NotificationCompat$Builder     // Catch:{ all -> 0x0016 }
            android.content.Context r6 = currentContext     // Catch:{ all -> 0x0016 }
            r5.<init>(r6, r4)     // Catch:{ all -> 0x0016 }
            goto L_0x001d
        L_0x0016:
            androidx.core.app.NotificationCompat$Builder r5 = new androidx.core.app.NotificationCompat$Builder
            android.content.Context r4 = currentContext
            r5.<init>(r4)
        L_0x001d:
            java.lang.String r4 = "alert"
            java.lang.String r4 = r1.optString(r4, r3)
            r6 = 1
            androidx.core.app.NotificationCompat$Builder r7 = r5.setAutoCancel(r6)
            int r8 = getSmallIconId(r1)
            androidx.core.app.NotificationCompat$Builder r7 = r7.setSmallIcon(r8)
            androidx.core.app.NotificationCompat$BigTextStyle r8 = new androidx.core.app.NotificationCompat$BigTextStyle
            r8.<init>()
            androidx.core.app.NotificationCompat$BigTextStyle r8 = r8.bigText(r4)
            androidx.core.app.NotificationCompat$Builder r7 = r7.setStyle(r8)
            androidx.core.app.NotificationCompat$Builder r7 = r7.setContentText(r4)
            r7.setTicker(r4)
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 24
            if (r7 < r8) goto L_0x0058
            java.lang.String r7 = "title"
            java.lang.String r7 = r1.optString(r7)
            java.lang.String r8 = ""
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x005f
        L_0x0058:
            java.lang.CharSequence r7 = getTitle(r1)
            r5.setContentTitle(r7)
        L_0x005f:
            java.math.BigInteger r7 = getAccentColor(r1)     // Catch:{ all -> 0x006c }
            if (r7 == 0) goto L_0x006c
            int r7 = r7.intValue()     // Catch:{ all -> 0x006c }
            r5.setColor(r7)     // Catch:{ all -> 0x006c }
        L_0x006c:
            boolean r7 = r1.has(r0)     // Catch:{ all -> 0x0080 }
            if (r7 == 0) goto L_0x007b
            java.lang.String r0 = r1.optString(r0)     // Catch:{ all -> 0x0080 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x0080 }
            goto L_0x007c
        L_0x007b:
            r0 = 1
        L_0x007c:
            r5.setVisibility(r0)     // Catch:{ all -> 0x0080 }
            goto L_0x0081
        L_0x0080:
        L_0x0081:
            android.graphics.Bitmap r0 = getLargeIcon(r1)
            if (r0 == 0) goto L_0x008c
            r2.hasLargeIcon = r6
            r5.setLargeIcon(r0)
        L_0x008c:
            java.lang.String r0 = "bicon"
            java.lang.String r0 = r1.optString(r0, r3)
            android.graphics.Bitmap r0 = getBitmap(r0)
            if (r0 == 0) goto L_0x00a8
            androidx.core.app.NotificationCompat$BigPictureStyle r3 = new androidx.core.app.NotificationCompat$BigPictureStyle
            r3.<init>()
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r3.bigPicture(r0)
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r0.setSummaryText(r4)
            r5.setStyle(r0)
        L_0x00a8:
            java.lang.Long r0 = r9.shownTimeStamp
            if (r0 == 0) goto L_0x00b9
            java.lang.Long r9 = r9.shownTimeStamp     // Catch:{ all -> 0x00b9 }
            long r3 = r9.longValue()     // Catch:{ all -> 0x00b9 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r6
            r5.setWhen(r3)     // Catch:{ all -> 0x00b9 }
        L_0x00b9:
            setAlertnessOptions(r1, r5)
            r2.compatBuilder = r5
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.getBaseOneSignalNotificationBuilder(com.onesignal.NotificationGenerationJob):com.onesignal.GenerateNotification$OneSignalNotificationBuilder");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void setAlertnessOptions(org.json.JSONObject r6, androidx.core.app.NotificationCompat.Builder r7) {
        /*
            java.lang.String r0 = "pri"
            r1 = 6
            int r0 = r6.optInt(r0, r1)
            int r0 = convertOSToAndroidPriority(r0)
            r7.setPriority(r0)
            r1 = 0
            r2 = 1
            if (r0 >= 0) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            if (r0 == 0) goto L_0x0018
            return
        L_0x0018:
            java.lang.String r0 = "ledc"
            boolean r3 = r6.has(r0)
            r4 = 4
            if (r3 == 0) goto L_0x0040
            java.lang.String r3 = "led"
            int r3 = r6.optInt(r3, r2)
            if (r3 != r2) goto L_0x0040
            java.math.BigInteger r3 = new java.math.BigInteger     // Catch:{ all -> 0x0040 }
            java.lang.String r0 = r6.optString(r0)     // Catch:{ all -> 0x0040 }
            r5 = 16
            r3.<init>(r0, r5)     // Catch:{ all -> 0x0040 }
            int r0 = r3.intValue()     // Catch:{ all -> 0x0040 }
            r3 = 2000(0x7d0, float:2.803E-42)
            r5 = 5000(0x1388, float:7.006E-42)
            r7.setLights(r0, r3, r5)     // Catch:{ all -> 0x0040 }
            goto L_0x0041
        L_0x0040:
            r1 = 4
        L_0x0041:
            boolean r0 = com.onesignal.OneSignal.getVibrate()
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = "vib"
            int r0 = r6.optInt(r0, r2)
            if (r0 != r2) goto L_0x0063
            java.lang.String r0 = "vib_pt"
            boolean r0 = r6.has(r0)
            if (r0 == 0) goto L_0x0061
            long[] r0 = com.onesignal.OSUtils.parseVibrationPattern(r6)
            if (r0 == 0) goto L_0x0063
            r7.setVibrate(r0)
            goto L_0x0063
        L_0x0061:
            r1 = r1 | 2
        L_0x0063:
            boolean r0 = isSoundEnabled(r6)
            if (r0 == 0) goto L_0x007e
            android.content.Context r0 = currentContext
            r2 = 0
            java.lang.String r3 = "sound"
            java.lang.String r6 = r6.optString(r3, r2)
            android.net.Uri r6 = com.onesignal.OSUtils.getSoundUri(r0, r6)
            if (r6 == 0) goto L_0x007c
            r7.setSound(r6)
            goto L_0x007e
        L_0x007c:
            r1 = r1 | 1
        L_0x007e:
            r7.setDefaults(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.setAlertnessOptions(org.json.JSONObject, androidx.core.app.NotificationCompat$Builder):void");
    }

    private static void removeNotifyOptions(NotificationCompat.Builder builder) {
        builder.setOnlyAlertOnce(true).setDefaults(0).setSound((Uri) null).setVibrate((long[]) null).setTicker((CharSequence) null);
    }

    private static void showNotification(NotificationGenerationJob notificationGenerationJob) {
        Notification notification;
        int intValue = notificationGenerationJob.getAndroidId().intValue();
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        String optString = jSONObject.optString("grp", (String) null);
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList = OneSignalNotificationManager.getActiveGrouplessNotifications(currentContext);
            if (optString == null && arrayList.size() >= 3) {
                optString = OneSignalNotificationManager.getGrouplessSummaryKey();
                OneSignalNotificationManager.assignGrouplessNotifications(currentContext, arrayList);
            }
        }
        OneSignalNotificationBuilder baseOneSignalNotificationBuilder = getBaseOneSignalNotificationBuilder(notificationGenerationJob);
        NotificationCompat.Builder builder = baseOneSignalNotificationBuilder.compatBuilder;
        addNotificationActionButtons(jSONObject, builder, intValue, (String) null);
        try {
            addBackgroundImage(jSONObject, builder);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not set background notification image!", th);
        }
        applyNotificationExtender(notificationGenerationJob, builder);
        if (notificationGenerationJob.restoring) {
            removeNotifyOptions(builder);
        }
        NotificationLimitManager.clearOldestOverLimit(currentContext, optString != null ? 2 : 1);
        if (optString != null) {
            createGenericPendingIntentsForGroup(builder, jSONObject, optString, intValue);
            notification = createSingleNotificationBeforeSummaryBuilder(notificationGenerationJob, builder);
            if (Build.VERSION.SDK_INT < 24 || !optString.equals(OneSignalNotificationManager.getGrouplessSummaryKey())) {
                createSummaryNotification(notificationGenerationJob, baseOneSignalNotificationBuilder);
            } else {
                createGrouplessSummaryNotification(notificationGenerationJob, arrayList.size() + 1);
            }
        } else {
            notification = createGenericPendingIntentsForNotif(builder, jSONObject, intValue);
        }
        if (optString == null || Build.VERSION.SDK_INT > 17) {
            addXiaomiSettings(baseOneSignalNotificationBuilder, notification);
            NotificationManagerCompat.from(currentContext).notify(intValue, notification);
        }
    }

    private static Notification createGenericPendingIntentsForNotif(NotificationCompat.Builder builder, JSONObject jSONObject, int i) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString())));
        builder.setDeleteIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(i)));
        return builder.build();
    }

    private static void createGenericPendingIntentsForGroup(NotificationCompat.Builder builder, JSONObject jSONObject, String str, int i) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString()).putExtra("grp", str)));
        builder.setDeleteIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(i).putExtra("grp", str)));
        builder.setGroup(str);
        try {
            builder.setGroupAlertBehavior(1);
        } catch (Throwable unused) {
        }
    }

    private static void applyNotificationExtender(NotificationGenerationJob notificationGenerationJob, NotificationCompat.Builder builder) {
        if (notificationGenerationJob.overrideSettings != null && notificationGenerationJob.overrideSettings.extender != null) {
            try {
                Field declaredField = NotificationCompat.Builder.class.getDeclaredField("mNotification");
                declaredField.setAccessible(true);
                Notification notification = (Notification) declaredField.get(builder);
                notificationGenerationJob.orgFlags = Integer.valueOf(notification.flags);
                notificationGenerationJob.orgSound = notification.sound;
                builder.extend(notificationGenerationJob.overrideSettings.extender);
                Notification notification2 = (Notification) declaredField.get(builder);
                Field declaredField2 = NotificationCompat.Builder.class.getDeclaredField("mContentText");
                declaredField2.setAccessible(true);
                Field declaredField3 = NotificationCompat.Builder.class.getDeclaredField("mContentTitle");
                declaredField3.setAccessible(true);
                notificationGenerationJob.overriddenBodyFromExtender = (CharSequence) declaredField2.get(builder);
                notificationGenerationJob.overriddenTitleFromExtender = (CharSequence) declaredField3.get(builder);
                if (!notificationGenerationJob.restoring) {
                    notificationGenerationJob.overriddenFlags = Integer.valueOf(notification2.flags);
                    notificationGenerationJob.overriddenSound = notification2.sound;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static Notification createSingleNotificationBeforeSummaryBuilder(NotificationGenerationJob notificationGenerationJob, NotificationCompat.Builder builder) {
        boolean z = Build.VERSION.SDK_INT > 17 && Build.VERSION.SDK_INT < 24 && !notificationGenerationJob.restoring;
        if (z && notificationGenerationJob.overriddenSound != null && !notificationGenerationJob.overriddenSound.equals(notificationGenerationJob.orgSound)) {
            builder.setSound((Uri) null);
        }
        Notification build = builder.build();
        if (z) {
            builder.setSound(notificationGenerationJob.overriddenSound);
        }
        return build;
    }

    private static void addXiaomiSettings(OneSignalNotificationBuilder oneSignalNotificationBuilder, Notification notification) {
        if (oneSignalNotificationBuilder.hasLargeIcon) {
            try {
                Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
                Field declaredField = newInstance.getClass().getDeclaredField("customizedIcon");
                declaredField.setAccessible(true);
                declaredField.set(newInstance, true);
                Field field = notification.getClass().getField("extraNotification");
                field.setAccessible(true);
                field.set(notification, newInstance);
            } catch (Throwable unused) {
            }
        }
    }

    static void updateSummaryNotification(NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        createSummaryNotification(notificationGenerationJob, (OneSignalNotificationBuilder) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0090 A[SYNTHETIC, Splitter:B:22:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void createSummaryNotification(com.onesignal.NotificationGenerationJob r25, com.onesignal.GenerateNotification.OneSignalNotificationBuilder r26) {
        /*
            r1 = r25
            r2 = r26
            java.lang.String r0 = "message"
            java.lang.String r3 = "title"
            java.lang.String r4 = "is_summary"
            java.lang.String r5 = "full_data"
            java.lang.String r6 = "android_notification_id"
            boolean r7 = r1.restoring
            org.json.JSONObject r8 = r1.jsonPayload
            java.lang.String r9 = "grp"
            r10 = 0
            java.lang.String r9 = r8.optString(r9, r10)
            java.security.SecureRandom r11 = new java.security.SecureRandom
            r11.<init>()
            int r12 = r11.nextInt()
            r13 = 0
            android.content.Intent r14 = getNewBaseDeleteIntent(r13)
            java.lang.String r15 = "summary"
            android.content.Intent r14 = r14.putExtra(r15, r9)
            android.app.PendingIntent r12 = getNewActionPendingIntent(r12, r14)
            android.content.Context r14 = currentContext
            com.onesignal.OneSignalDbHelper r14 = com.onesignal.OneSignalDbHelper.getInstance(r14)
            android.database.sqlite.SQLiteDatabase r15 = r14.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x02f0 }
            java.lang.String[] r17 = new java.lang.String[]{r6, r5, r4, r3, r0}     // Catch:{ all -> 0x02f0 }
            java.lang.String r10 = "group_id = ? AND dismissed = 0 AND opened = 0"
            r13 = 1
            r23 = r8
            java.lang.String[] r8 = new java.lang.String[r13]     // Catch:{ all -> 0x02ec }
            r16 = 0
            r8[r16] = r9     // Catch:{ all -> 0x02ec }
            if (r7 != 0) goto L_0x0076
            java.lang.Integer r16 = r25.getAndroidId()     // Catch:{ all -> 0x0072 }
            int r13 = r16.intValue()     // Catch:{ all -> 0x0072 }
            r2 = -1
            if (r13 == r2) goto L_0x0076
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r2.<init>()     // Catch:{ all -> 0x0072 }
            r2.append(r10)     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = " AND android_notification_id <> "
            r2.append(r10)     // Catch:{ all -> 0x0072 }
            java.lang.Integer r10 = r25.getAndroidId()     // Catch:{ all -> 0x0072 }
            r2.append(r10)     // Catch:{ all -> 0x0072 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0072 }
            r18 = r2
            goto L_0x0078
        L_0x0072:
            r0 = move-exception
            r10 = 0
            goto L_0x02f2
        L_0x0076:
            r18 = r10
        L_0x0078:
            java.lang.String r16 = "notification"
            r20 = 0
            r21 = 0
            java.lang.String r22 = "_id DESC"
            r19 = r8
            android.database.Cursor r2 = r15.query(r16, r17, r18, r19, r20, r21, r22)     // Catch:{ all -> 0x02ec }
            boolean r8 = r2.moveToFirst()     // Catch:{ all -> 0x02e9 }
            java.lang.String r10 = " "
            java.lang.String r13 = ""
            if (r8 == 0) goto L_0x013b
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02e9 }
            r8.<init>()     // Catch:{ all -> 0x02e9 }
            r15 = 0
            r16 = 0
        L_0x0098:
            r17 = r12
            int r12 = r2.getColumnIndex(r4)     // Catch:{ all -> 0x02e9 }
            int r12 = r2.getInt(r12)     // Catch:{ all -> 0x02e9 }
            r18 = r4
            r4 = 1
            if (r12 != r4) goto L_0x00b8
            int r4 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x02e9 }
            int r4 = r2.getInt(r4)     // Catch:{ all -> 0x02e9 }
            java.lang.Integer r16 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x02e9 }
            r19 = r0
            r20 = r3
            goto L_0x0114
        L_0x00b8:
            int r4 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x02e9 }
            java.lang.String r4 = r2.getString(r4)     // Catch:{ all -> 0x02e9 }
            if (r4 != 0) goto L_0x00c4
            r4 = r13
            goto L_0x00d3
        L_0x00c4:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x02e9 }
            r12.<init>()     // Catch:{ all -> 0x02e9 }
            r12.append(r4)     // Catch:{ all -> 0x02e9 }
            r12.append(r10)     // Catch:{ all -> 0x02e9 }
            java.lang.String r4 = r12.toString()     // Catch:{ all -> 0x02e9 }
        L_0x00d3:
            int r12 = r2.getColumnIndex(r0)     // Catch:{ all -> 0x02e9 }
            java.lang.String r12 = r2.getString(r12)     // Catch:{ all -> 0x02e9 }
            r19 = r0
            android.text.SpannableString r0 = new android.text.SpannableString     // Catch:{ all -> 0x02e9 }
            r20 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02e9 }
            r3.<init>()     // Catch:{ all -> 0x02e9 }
            r3.append(r4)     // Catch:{ all -> 0x02e9 }
            r3.append(r12)     // Catch:{ all -> 0x02e9 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02e9 }
            r0.<init>(r3)     // Catch:{ all -> 0x02e9 }
            int r3 = r4.length()     // Catch:{ all -> 0x02e9 }
            if (r3 <= 0) goto L_0x0107
            android.text.style.StyleSpan r3 = new android.text.style.StyleSpan     // Catch:{ all -> 0x02e9 }
            r12 = 1
            r3.<init>(r12)     // Catch:{ all -> 0x02e9 }
            int r4 = r4.length()     // Catch:{ all -> 0x02e9 }
            r12 = 0
            r0.setSpan(r3, r12, r4, r12)     // Catch:{ all -> 0x02e9 }
        L_0x0107:
            r8.add(r0)     // Catch:{ all -> 0x02e9 }
            if (r15 != 0) goto L_0x0114
            int r0 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x02e9 }
            java.lang.String r15 = r2.getString(r0)     // Catch:{ all -> 0x02e9 }
        L_0x0114:
            boolean r0 = r2.moveToNext()     // Catch:{ all -> 0x02e9 }
            if (r0 != 0) goto L_0x0131
            if (r7 == 0) goto L_0x012d
            if (r15 == 0) goto L_0x012d
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0129 }
            r0.<init>(r15)     // Catch:{ JSONException -> 0x0129 }
            r24 = r8
            r8 = r0
            r0 = r24
            goto L_0x0142
        L_0x0129:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x02e9 }
        L_0x012d:
            r0 = r8
            r8 = r23
            goto L_0x0142
        L_0x0131:
            r12 = r17
            r4 = r18
            r0 = r19
            r3 = r20
            goto L_0x0098
        L_0x013b:
            r17 = r12
            r8 = r23
            r0 = 0
            r16 = 0
        L_0x0142:
            if (r2 == 0) goto L_0x014d
            boolean r3 = r2.isClosed()
            if (r3 != 0) goto L_0x014d
            r2.close()
        L_0x014d:
            if (r16 != 0) goto L_0x015e
            int r2 = r11.nextInt()
            java.lang.Integer r16 = java.lang.Integer.valueOf(r2)
            int r2 = r16.intValue()
            createSummaryIdDatabaseEntry(r14, r9, r2)
        L_0x015e:
            int r2 = r11.nextInt()
            int r3 = r16.intValue()
            android.content.Intent r3 = createBaseSummaryIntent(r3, r8, r9)
            android.app.PendingIntent r2 = getNewActionPendingIntent(r2, r3)
            if (r0 == 0) goto L_0x02a6
            if (r7 == 0) goto L_0x0179
            int r3 = r0.size()
            r4 = 1
            if (r3 > r4) goto L_0x0181
        L_0x0179:
            if (r7 != 0) goto L_0x02a6
            int r3 = r0.size()
            if (r3 <= 0) goto L_0x02a6
        L_0x0181:
            int r3 = r0.size()
            r4 = r7 ^ 1
            int r3 = r3 + r4
            java.lang.String r4 = "grp_msg"
            r5 = 0
            java.lang.String r4 = r8.optString(r4, r5)
            if (r4 != 0) goto L_0x01a3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r6 = " new messages"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            goto L_0x01b8
        L_0x01a3:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r13)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            java.lang.String r8 = "$[notif_count]"
            java.lang.String r4 = r4.replace(r8, r6)
        L_0x01b8:
            com.onesignal.GenerateNotification$OneSignalNotificationBuilder r6 = getBaseOneSignalNotificationBuilder(r25)
            androidx.core.app.NotificationCompat$Builder r6 = r6.compatBuilder
            if (r7 == 0) goto L_0x01c4
            removeNotifyOptions(r6)
            goto L_0x01da
        L_0x01c4:
            android.net.Uri r8 = r1.overriddenSound
            if (r8 == 0) goto L_0x01cd
            android.net.Uri r8 = r1.overriddenSound
            r6.setSound(r8)
        L_0x01cd:
            java.lang.Integer r8 = r1.overriddenFlags
            if (r8 == 0) goto L_0x01da
            java.lang.Integer r8 = r1.overriddenFlags
            int r8 = r8.intValue()
            r6.setDefaults(r8)
        L_0x01da:
            androidx.core.app.NotificationCompat$Builder r2 = r6.setContentIntent(r2)
            r11 = r17
            androidx.core.app.NotificationCompat$Builder r2 = r2.setDeleteIntent(r11)
            android.content.Context r8 = currentContext
            android.content.pm.PackageManager r8 = r8.getPackageManager()
            android.content.Context r11 = currentContext
            android.content.pm.ApplicationInfo r11 = r11.getApplicationInfo()
            java.lang.CharSequence r8 = r8.getApplicationLabel(r11)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setContentTitle(r8)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setContentText(r4)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setNumber(r3)
            int r3 = getDefaultSmallIconId()
            androidx.core.app.NotificationCompat$Builder r2 = r2.setSmallIcon(r3)
            android.graphics.Bitmap r3 = getDefaultLargeIcon()
            androidx.core.app.NotificationCompat$Builder r2 = r2.setLargeIcon(r3)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setOnlyAlertOnce(r7)
            r3 = 0
            androidx.core.app.NotificationCompat$Builder r2 = r2.setAutoCancel(r3)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setGroup(r9)
            r3 = 1
            r2.setGroupSummary(r3)
            r6.setGroupAlertBehavior(r3)     // Catch:{ all -> 0x0225 }
            goto L_0x0226
        L_0x0225:
        L_0x0226:
            if (r7 != 0) goto L_0x022b
            r6.setTicker(r4)
        L_0x022b:
            androidx.core.app.NotificationCompat$InboxStyle r2 = new androidx.core.app.NotificationCompat$InboxStyle
            r2.<init>()
            if (r7 != 0) goto L_0x0287
            java.lang.CharSequence r3 = r25.getTitle()
            if (r3 == 0) goto L_0x0241
            java.lang.CharSequence r3 = r25.getTitle()
            java.lang.String r3 = r3.toString()
            goto L_0x0242
        L_0x0241:
            r3 = r5
        L_0x0242:
            if (r3 != 0) goto L_0x0245
            goto L_0x0254
        L_0x0245:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r3)
            r5.append(r10)
            java.lang.String r13 = r5.toString()
        L_0x0254:
            java.lang.CharSequence r1 = r25.getBody()
            java.lang.String r1 = r1.toString()
            android.text.SpannableString r3 = new android.text.SpannableString
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r13)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            r3.<init>(r1)
            int r1 = r13.length()
            if (r1 <= 0) goto L_0x0284
            android.text.style.StyleSpan r1 = new android.text.style.StyleSpan
            r5 = 1
            r1.<init>(r5)
            int r5 = r13.length()
            r7 = 0
            r3.setSpan(r1, r7, r5, r7)
        L_0x0284:
            r2.addLine(r3)
        L_0x0287:
            java.util.Iterator r0 = r0.iterator()
        L_0x028b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x029b
            java.lang.Object r1 = r0.next()
            android.text.SpannableString r1 = (android.text.SpannableString) r1
            r2.addLine(r1)
            goto L_0x028b
        L_0x029b:
            r2.setBigContentTitle(r4)
            r6.setStyle(r2)
            android.app.Notification r0 = r6.build()
            goto L_0x02db
        L_0x02a6:
            r11 = r17
            r1 = r26
            androidx.core.app.NotificationCompat$Builder r0 = r1.compatBuilder
            java.util.ArrayList<androidx.core.app.NotificationCompat$Action> r3 = r0.mActions
            r3.clear()
            int r3 = r16.intValue()
            addNotificationActionButtons(r8, r0, r3, r9)
            androidx.core.app.NotificationCompat$Builder r2 = r0.setContentIntent(r2)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setDeleteIntent(r11)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setOnlyAlertOnce(r7)
            r3 = 0
            androidx.core.app.NotificationCompat$Builder r2 = r2.setAutoCancel(r3)
            androidx.core.app.NotificationCompat$Builder r2 = r2.setGroup(r9)
            r3 = 1
            r2.setGroupSummary(r3)
            r0.setGroupAlertBehavior(r3)     // Catch:{ all -> 0x02d4 }
        L_0x02d4:
            android.app.Notification r0 = r0.build()
            addXiaomiSettings(r1, r0)
        L_0x02db:
            android.content.Context r1 = currentContext
            androidx.core.app.NotificationManagerCompat r1 = androidx.core.app.NotificationManagerCompat.from(r1)
            int r2 = r16.intValue()
            r1.notify(r2, r0)
            return
        L_0x02e9:
            r0 = move-exception
            r10 = r2
            goto L_0x02f2
        L_0x02ec:
            r0 = move-exception
            r5 = 0
            r10 = r5
            goto L_0x02f2
        L_0x02f0:
            r0 = move-exception
            r5 = r10
        L_0x02f2:
            if (r10 == 0) goto L_0x02fd
            boolean r1 = r10.isClosed()
            if (r1 != 0) goto L_0x02fd
            r10.close()
        L_0x02fd:
            goto L_0x02ff
        L_0x02fe:
            throw r0
        L_0x02ff:
            goto L_0x02fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.createSummaryNotification(com.onesignal.NotificationGenerationJob, com.onesignal.GenerateNotification$OneSignalNotificationBuilder):void");
    }

    private static void createGrouplessSummaryNotification(NotificationGenerationJob notificationGenerationJob, int i) {
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        SecureRandom secureRandom = new SecureRandom();
        String grouplessSummaryKey = OneSignalNotificationManager.getGrouplessSummaryKey();
        String str = i + " new messages";
        int grouplessSummaryId = OneSignalNotificationManager.getGrouplessSummaryId();
        PendingIntent newActionPendingIntent = getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(grouplessSummaryId, jSONObject, grouplessSummaryKey));
        PendingIntent newActionPendingIntent2 = getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(0).putExtra("summary", grouplessSummaryKey));
        NotificationCompat.Builder builder = getBaseOneSignalNotificationBuilder(notificationGenerationJob).compatBuilder;
        if (notificationGenerationJob.overriddenSound != null) {
            builder.setSound(notificationGenerationJob.overriddenSound);
        }
        if (notificationGenerationJob.overriddenFlags != null) {
            builder.setDefaults(notificationGenerationJob.overriddenFlags.intValue());
        }
        builder.setContentIntent(newActionPendingIntent).setDeleteIntent(newActionPendingIntent2).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(str).setNumber(i).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(true).setAutoCancel(false).setGroup(grouplessSummaryKey).setGroupSummary(true);
        try {
            builder.setGroupAlertBehavior(1);
        } catch (Throwable unused) {
        }
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(str);
        builder.setStyle(inboxStyle);
        NotificationManagerCompat.from(currentContext).notify(grouplessSummaryId, builder.build());
    }

    private static Intent createBaseSummaryIntent(int i, JSONObject jSONObject, String str) {
        return getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString()).putExtra("summary", str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042 A[SYNTHETIC, Splitter:B:14:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void createSummaryIdDatabaseEntry(com.onesignal.OneSignalDbHelper r4, java.lang.String r5, int r6) {
        /*
            java.lang.String r0 = "Error closing transaction! "
            r1 = 0
            android.database.sqlite.SQLiteDatabase r4 = r4.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0038 }
            r4.beginTransaction()     // Catch:{ all -> 0x0035 }
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ all -> 0x0035 }
            r2.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = "android_notification_id"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0035 }
            r2.put(r3, r6)     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = "group_id"
            r2.put(r6, r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "is_summary"
            r6 = 1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0035 }
            r2.put(r5, r6)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "notification"
            r4.insertOrThrow(r5, r1, r2)     // Catch:{ all -> 0x0035 }
            r4.setTransactionSuccessful()     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x004c
            r4.endTransaction()     // Catch:{ all -> 0x0046 }
            goto L_0x004c
        L_0x0035:
            r5 = move-exception
            r1 = r4
            goto L_0x0039
        L_0x0038:
            r5 = move-exception
        L_0x0039:
            com.onesignal.OneSignal$LOG_LEVEL r4 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x004d }
            java.lang.String r6 = "Error adding summary notification record! "
            com.onesignal.OneSignal.Log(r4, r6, r5)     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x004c
            r1.endTransaction()     // Catch:{ all -> 0x0046 }
            goto L_0x004c
        L_0x0046:
            r4 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r5 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
            com.onesignal.OneSignal.Log(r5, r0, r4)
        L_0x004c:
            return
        L_0x004d:
            r4 = move-exception
            if (r1 == 0) goto L_0x005a
            r1.endTransaction()     // Catch:{ all -> 0x0054 }
            goto L_0x005a
        L_0x0054:
            r5 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
            com.onesignal.OneSignal.Log(r6, r0, r5)
        L_0x005a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.createSummaryIdDatabaseEntry(com.onesignal.OneSignalDbHelper, java.lang.String, int):void");
    }

    private static void addBackgroundImage(JSONObject jSONObject, NotificationCompat.Builder builder) throws Throwable {
        JSONObject jSONObject2;
        Bitmap bitmap;
        String str;
        if (Build.VERSION.SDK_INT >= 16) {
            String optString = jSONObject.optString("bg_img", (String) null);
            if (optString != null) {
                jSONObject2 = new JSONObject(optString);
                bitmap = getBitmap(jSONObject2.optString("img", (String) null));
            } else {
                bitmap = null;
                jSONObject2 = null;
            }
            if (bitmap == null) {
                bitmap = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
            }
            if (bitmap != null) {
                RemoteViews remoteViews = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
                remoteViews.setTextViewText(R.id.os_bgimage_notif_title, getTitle(jSONObject));
                remoteViews.setTextViewText(R.id.os_bgimage_notif_body, jSONObject.optString("alert"));
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
                if (jSONObject2 == null || !jSONObject2.has("img_align")) {
                    int identifier = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", packageName);
                    str = identifier != 0 ? contextResources.getString(identifier) : null;
                } else {
                    str = jSONObject2.getString("img_align");
                }
                if ("right".equals(str)) {
                    remoteViews.setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, bitmap);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage, 2);
                } else {
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage, bitmap);
                }
                builder.setContent(remoteViews);
                builder.setStyle((NotificationCompat.Style) null);
            }
        }
    }

    private static void setTextColor(RemoteViews remoteViews, JSONObject jSONObject, int i, String str, String str2) {
        Integer safeGetColorFromHex = safeGetColorFromHex(jSONObject, str);
        if (safeGetColorFromHex != null) {
            remoteViews.setTextColor(i, safeGetColorFromHex.intValue());
            return;
        }
        int identifier = contextResources.getIdentifier(str2, "color", packageName);
        if (identifier != 0) {
            remoteViews.setTextColor(i, AndroidSupportV4Compat.ContextCompat.getColor(currentContext, identifier));
        }
    }

    private static Integer safeGetColorFromHex(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            if (jSONObject.has(str)) {
                return Integer.valueOf(new BigInteger(jSONObject.optString(str), 16).intValue());
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Bitmap getLargeIcon(JSONObject jSONObject) {
        Bitmap bitmap = getBitmap(jSONObject.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        return resizeBitmapForLargeIconArea(bitmap);
    }

    private static Bitmap getDefaultLargeIcon() {
        return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
    }

    private static Bitmap resizeBitmapForLargeIconArea(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int dimension = (int) contextResources.getDimension(17104902);
            int dimension2 = (int) contextResources.getDimension(17104901);
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            if (width <= dimension2 && height <= dimension) {
                return bitmap;
            }
            if (height > width) {
                dimension2 = (int) (((float) dimension) * (((float) width) / ((float) height)));
            } else if (width > height) {
                dimension = (int) (((float) dimension2) * (((float) height) / ((float) width)));
            }
            return Bitmap.createScaledBitmap(bitmap, dimension2, dimension, true);
        } catch (Throwable unused) {
            return bitmap;
        }
    }

    private static Bitmap getBitmapFromAssetsOrResourceName(String str) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str));
        } catch (Throwable unused) {
            bitmap = null;
        }
        if (bitmap != null) {
            return bitmap;
        }
        try {
            for (String str2 : Arrays.asList(new String[]{".png", ".webp", ".jpg", ".gif", ".bmp"})) {
                try {
                    bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str + str2));
                    continue;
                } catch (Throwable unused2) {
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            int resourceIcon = getResourceIcon(str);
            if (resourceIcon != 0) {
                return BitmapFactory.decodeResource(contextResources, resourceIcon);
            }
        } catch (Throwable unused3) {
        }
        return null;
    }

    private static Bitmap getBitmapFromURL(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Could not download image!", th);
            return null;
        }
    }

    private static Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("http://") || trim.startsWith("https://")) {
            return getBitmapFromURL(trim);
        }
        return getBitmapFromAssetsOrResourceName(str);
    }

    private static int getResourceIcon(String str) {
        if (str == null) {
            return 0;
        }
        String trim = str.trim();
        if (!OSUtils.isValidResourceName(trim)) {
            return 0;
        }
        int drawableId = getDrawableId(trim);
        if (drawableId != 0) {
            return drawableId;
        }
        try {
            return R.drawable.class.getField(str).getInt((Object) null);
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static int getSmallIconId(JSONObject jSONObject) {
        int resourceIcon = getResourceIcon(jSONObject.optString("sicon", (String) null));
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        return getDefaultSmallIconId();
    }

    private static int getDefaultSmallIconId() {
        int drawableId = getDrawableId("ic_stat_onesignal_default");
        if (drawableId != 0) {
            return drawableId;
        }
        int drawableId2 = getDrawableId("corona_statusbar_icon_default");
        if (drawableId2 != 0) {
            return drawableId2;
        }
        int drawableId3 = getDrawableId("ic_os_notification_fallback_white_24dp");
        if (drawableId3 != 0) {
            return drawableId3;
        }
        return 17301598;
    }

    private static int getDrawableId(String str) {
        return contextResources.getIdentifier(str, "drawable", packageName);
    }

    private static boolean isSoundEnabled(JSONObject jSONObject) {
        String optString = jSONObject.optString("sound", (String) null);
        if ("null".equals(optString) || "nil".equals(optString)) {
            return false;
        }
        return OneSignal.getSoundEnabled();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[Catch:{ all -> 0x0025 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.math.BigInteger getAccentColor(org.json.JSONObject r4) {
        /*
            java.lang.String r0 = "bgac"
            r1 = 16
            r2 = 0
            boolean r3 = r4.has(r0)     // Catch:{ all -> 0x0015 }
            if (r3 == 0) goto L_0x0015
            java.math.BigInteger r3 = new java.math.BigInteger     // Catch:{ all -> 0x0015 }
            java.lang.String r4 = r4.optString(r0, r2)     // Catch:{ all -> 0x0015 }
            r3.<init>(r4, r1)     // Catch:{ all -> 0x0015 }
            return r3
        L_0x0015:
            android.content.Context r4 = currentContext     // Catch:{ all -> 0x0025 }
            java.lang.String r0 = "com.onesignal.NotificationAccentColor.DEFAULT"
            java.lang.String r4 = com.onesignal.OSUtils.getManifestMeta(r4, r0)     // Catch:{ all -> 0x0025 }
            if (r4 == 0) goto L_0x0025
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch:{ all -> 0x0025 }
            r0.<init>(r4, r1)     // Catch:{ all -> 0x0025 }
            return r0
        L_0x0025:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.getAccentColor(org.json.JSONObject):java.math.BigInteger");
    }

    private static void addNotificationActionButtons(JSONObject jSONObject, NotificationCompat.Builder builder, int i, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString(AdType.CUSTOM));
            if (jSONObject2.has("a")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("a");
                if (jSONObject3.has("actionButtons")) {
                    JSONArray jSONArray = jSONObject3.getJSONArray("actionButtons");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        JSONObject jSONObject4 = new JSONObject(jSONObject.toString());
                        Intent newBaseIntent = getNewBaseIntent(i);
                        newBaseIntent.setAction("" + i2);
                        newBaseIntent.putExtra("action_button", true);
                        jSONObject4.put("actionId", optJSONObject.optString("id"));
                        newBaseIntent.putExtra("onesignalData", jSONObject4.toString());
                        if (str != null) {
                            newBaseIntent.putExtra("summary", str);
                        } else if (jSONObject.has("grp")) {
                            newBaseIntent.putExtra("grp", jSONObject.optString("grp"));
                        }
                        builder.addAction(optJSONObject.has("icon") ? getResourceIcon(optJSONObject.optString("icon")) : 0, optJSONObject.optString("text"), getNewActionPendingIntent(i, newBaseIntent));
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void addAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) {
        try {
            addCustomAlertButtons(context, jSONObject, list, list2);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Failed to parse JSON for custom buttons for alert dialog.", th);
        }
        if (list.size() == 0 || list.size() < 3) {
            list.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
            list2.add("__DEFAULT__");
        }
    }

    private static void addCustomAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.optString(AdType.CUSTOM));
        if (jSONObject2.has("a")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("a");
            if (jSONObject3.has("actionButtons")) {
                JSONArray optJSONArray = jSONObject3.optJSONArray("actionButtons");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject4 = optJSONArray.getJSONObject(i);
                    list.add(jSONObject4.optString("text"));
                    list2.add(jSONObject4.optString("id"));
                }
            }
        }
    }
}
