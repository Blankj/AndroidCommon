package com.blankj.common.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.blankj.common.BuildConfig;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/30
 *     desc  : 基类App
 * </pre>
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    public static Context getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Utils.init(this);
        registerActivityLifecycleCallbacks(mCallbacks);
        new LogUtils.Builder().setLogSwitch(BuildConfig.DEBUG);
    }

    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LogUtils.w(activity + " onActivityCreated");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogUtils.w(activity + "onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            LogUtils.w(activity + "onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            LogUtils.w(activity + "onActivityPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            LogUtils.w(activity + "onActivityStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            LogUtils.w(activity + "onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogUtils.w(activity + "onActivityDestroyed");
        }
    };
}
