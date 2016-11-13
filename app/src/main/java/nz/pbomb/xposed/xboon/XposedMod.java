package nz.pbomb.xposed.xboon;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage {
    private final static String PKG_NAME = "de.wirecard.boon";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(lpparam.packageName.equals(PKG_NAME)) {
            // Whitelist Check Bypass
            XposedHelpers.findAndHookMethod(PKG_NAME+".whitelist.a$1", lpparam.classLoader, "a", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });

            // Root Check Bypass 1
            XposedHelpers.findAndHookMethod("com.wirecard.hce.core.j.g", lpparam.classLoader, "a", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });

            // Root Check Bypass 1
            XposedHelpers.findAndHookMethod("c.a.a.a.CrashDetails", lpparam.classLoader, "e", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("false");
                }
            });
        }
    }
}
