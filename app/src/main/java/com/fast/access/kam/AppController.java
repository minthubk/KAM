package com.fast.access.kam;

import android.app.Application;
import android.os.StrictMode;

import com.fast.access.kam.activities.Home;
import com.fast.access.kam.global.helper.BitmapCache;
import com.squareup.leakcanary.LeakCanary;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by Kosh on 8/16/2015. copyrights are reserved
 */
public class AppController extends Application {

    private static AppController controller;
    private BitmapCache bitmapCache;

    @Override
    public void onCreate() {
        super.onCreate();
        controller = this;
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        LeakCanary.install(this);
        CustomActivityOnCrash.setRestartActivityClass(Home.class);
        CustomActivityOnCrash.install(this);
    }

    public static AppController getController() {
        return controller;
    }

    public BitmapCache getBitmapCache() {
        if (bitmapCache == null) {
            bitmapCache = new BitmapCache();
        }
        return bitmapCache;
    }
}
