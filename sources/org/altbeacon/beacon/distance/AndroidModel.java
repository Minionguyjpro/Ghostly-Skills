package org.altbeacon.beacon.distance;

import android.os.Build;
import org.altbeacon.beacon.logging.LogManager;

public class AndroidModel {
    String mBuildNumber;
    String mManufacturer;
    String mModel;
    String mVersion;

    public AndroidModel(String str, String str2, String str3, String str4) {
        this.mVersion = str;
        this.mBuildNumber = str2;
        this.mModel = str3;
        this.mManufacturer = str4;
    }

    public static AndroidModel forThisDevice() {
        return new AndroidModel(Build.VERSION.RELEASE, Build.ID, Build.MODEL, Build.MANUFACTURER);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getBuildNumber() {
        return this.mBuildNumber;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public int matchScore(AndroidModel androidModel) {
        int equalsIgnoreCase = this.mManufacturer.equalsIgnoreCase(androidModel.mManufacturer);
        if (equalsIgnoreCase == 1 && this.mModel.equals(androidModel.mModel)) {
            equalsIgnoreCase = 2;
        }
        if (equalsIgnoreCase == 2 && this.mBuildNumber.equals(androidModel.mBuildNumber)) {
            equalsIgnoreCase = 3;
        }
        if (equalsIgnoreCase == 3 && this.mVersion.equals(androidModel.mVersion)) {
            equalsIgnoreCase = 4;
        }
        LogManager.d("AndroidModel", "Score is %s for %s compared to %s", Integer.valueOf(equalsIgnoreCase), toString(), androidModel);
        return equalsIgnoreCase;
    }

    public String toString() {
        return "" + this.mManufacturer + ";" + this.mModel + ";" + this.mBuildNumber + ";" + this.mVersion;
    }
}
