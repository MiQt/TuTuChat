package com.miqt.tutu.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.miqt.tlchat.tulingchat.R;

/**
 * Created by IEZ on 2016/8/9.
 */
public class UpDateAppManager {
    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
