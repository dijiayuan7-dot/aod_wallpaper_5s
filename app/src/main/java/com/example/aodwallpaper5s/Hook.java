package com.example.aodwallpaper5s;
import android.os.Handler;
import android.os.Looper;
import de.robv.android.xposed.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
public final class Hook {
    private final Handler handler = new Handler(Looper.getMainLooper());
    public void install(LoadPackageParam p) throws Throwable {
        final Class<?> c = XposedHelpers.findClass("com.android.systemui.doze.DozeService", p.classLoader);
        XposedBridge.hookAllMethods(c, "onDreamingStarted", new XC_MethodHook() {
            protected void afterHookedMethod(final MethodHookParam h) {
                try { XposedHelpers.callMethod(h.thisObject, "setDozeScreenState", 2); XposedBridge.log("AOD Wallpaper 5s: wallpaper ON"); } catch (Throwable t) { XposedBridge.log(t); }
                handler.postDelayed(new Runnable() { public void run() { try { XposedHelpers.callMethod(h.thisObject, "setDozeScreenState", 4); XposedBridge.log("AOD Wallpaper 5s: STATE_DOZE_SUSPEND=4"); } catch (Throwable t) { XposedBridge.log(t); } } }, 5000L);
            }
        });
        XposedBridge.hookAllMethods(c, "onDreamingStopped", new XC_MethodHook() { protected void beforeHookedMethod(MethodHookParam h) { handler.removeCallbacksAndMessages(null); } });
    }
}
