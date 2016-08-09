package com.miqt.tutu.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import com.miqt.tlchat.tulingchat.R;
import com.miqt.tutu.TuTuUrls;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * 启动界面
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_start);
        update();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                //判断需不需要登录
//                if (BmobUser.getCurrentUser() == null) {
//                    intent = new Intent(StartActivity.this, LoginActivity.class);
//                } else {
                intent = new Intent(StartActivity.this, MainActivity.class);
//                }
                startActivity(intent);
                finish();
            }
            //等待时间
        }, 1500);
    }

    private void update() {
        RequestParams pramera = new RequestParams(TuTuUrls.VERSTION);
        x.http().get(pramera, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("Verstation result=", result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
