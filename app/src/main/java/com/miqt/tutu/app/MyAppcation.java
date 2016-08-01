package com.miqt.tutu.app;

import android.app.Application;
import android.util.Log;

import com.miqt.tlchat.tulingchat.BuildConfig;
import com.miqt.tutu.activity.MainActivity;
import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;


import cn.bmob.v3.Bmob;

/**
 * Created by IEZ on 2016/8/1.
 */
public class MyAppcation extends Application {
    private static final String TAG = "MyAppcation";

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化  SDK
        Bmob.initialize(this, "31558ea36a284e51b5a572035f7e277c");
    }
}
