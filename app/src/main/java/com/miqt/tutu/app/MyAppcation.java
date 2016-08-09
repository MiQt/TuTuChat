package com.miqt.tutu.app;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;

import org.xutils.x;

import cn.bmob.v3.Bmob;

public class MyAppcation extends Application {
    private static final String TAG = "MyAppcation";
    private TuringApiManager mTuringApiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // 初始化  SDK
        Bmob.initialize(this, "31558ea36a284e51b5a572035f7e277c");

    }

    public TuringApiManager getTuringApiManager() {
        return mTuringApiManager;
    }
}
