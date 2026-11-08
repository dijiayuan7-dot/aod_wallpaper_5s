package com.example.aodwallpaper5s;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedBridge;
public final class Entry implements IXposedHookLoadPackage {
    public void handleLoadPackage(LoadPackageParam p) throws Throwable {
        if (!"com.android.systemui".equals(p.packageName)) return;
        XposedBridge.log("AOD Wallpaper 5s loaded for SystemUI");
        new Hook().install(p);
    }
}
