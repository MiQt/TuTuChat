package com.miqt.tutu.app;

import android.app.Application;
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
        // turingSDK初始化
        SDKInitBuilder builder = new SDKInitBuilder(this)
                .setSecret("d257a9b9d5b47d5f").setTuringKey("a9776345bca744c288d8e14a5322939f").setUniqueId("123");
        SDKInit.init(builder, new InitListener() {
            @Override
            public void onFail(String error) {
                Log.d(TAG, error);
            }

            @Override
            public void onComplete() {
                // 获取userid成功后，才可以请求Turing服务器，需要请求必须在此回调成功，才可正确请求
                mTuringApiManager = new TuringApiManager(MyAppcation.this);
            }
        });
    }

    public TuringApiManager getTuringApiManager() {
        return mTuringApiManager;
    }
}
