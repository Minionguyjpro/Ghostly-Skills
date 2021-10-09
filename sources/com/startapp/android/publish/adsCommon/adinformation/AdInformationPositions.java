package com.startapp.android.publish.adsCommon.adinformation;

import android.widget.RelativeLayout;

/* compiled from: StartAppSDK */
public class AdInformationPositions {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f202a = Position.BOTTOM_LEFT.name();

    /* compiled from: StartAppSDK */
    public enum Position {
        TOP_LEFT(1, new int[]{10, 9}, -1),
        TOP_RIGHT(2, new int[]{10, 11}, 1),
        BOTTOM_LEFT(3, new int[]{12, 9}, -1),
        BOTTOM_RIGHT(4, new int[]{12, 11}, 1);
        
        private int animationMultiplier;
        private int index;
        private int[] rules;

        private Position(int i, int[] iArr, int i2) {
            this.rules = iArr;
            this.animationMultiplier = i2;
            this.index = i;
        }

        public void addRules(RelativeLayout.LayoutParams layoutParams) {
            int i = 0;
            while (true) {
                int[] iArr = this.rules;
                if (i < iArr.length) {
                    layoutParams.addRule(iArr[i]);
                    i++;
                } else {
                    return;
                }
            }
        }

        public int getIndex() {
            return this.index;
        }

        public int getAnimationStartMultiplier() {
            return this.animationMultiplier;
        }

        public static Position getByName(String str) {
            Position position = BOTTOM_LEFT;
            Position[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    position = values[i];
                }
            }
            return position;
        }

        public static Position getByIndex(long j) {
            Position position = BOTTOM_LEFT;
            Position[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (((long) values[i].getIndex()) == j) {
                    position = values[i];
                }
            }
            return position;
        }
    }
}
