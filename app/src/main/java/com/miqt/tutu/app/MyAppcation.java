package com.miqt.tutu.app;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.miqt.tlchat.tulingchat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
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
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.cacheInMemory(true);
        options.cacheOnDisk(true);
        options.showImageOnFail(R.mipmap.ic_launcher);
        config.defaultDisplayImageOptions(options.build());
        ImageLoader.getInstance().init(config.build());
    }

    public TuringApiManager getTuringApiManager() {
        return mTuringApiManager;
    }
}
