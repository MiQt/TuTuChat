package com.miqt.tutu.activity;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miqt.tlchat.tulingchat.R;
import com.miqt.tutu.app.MyAppcation;
import com.miqt.tutu.entity.Message;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity，fdssaff";
    private TuringApiManager mTuringApiManager;

    Button bt_send;
    LinearLayout ll_results;
    EditText et_message;
    private LayoutInflater mLayoutInflater;
    ScrollView mScrollView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayoutInflater = LayoutInflater.from(this);
        handler = new Handler();
        bt_send = (Button) findViewById(R.id.bt_send);
        mScrollView = (ScrollView) findViewById(R.id.scrolview);
        ll_results = (LinearLayout) findViewById(R.id.ll_results);
        et_message = (EditText) findViewById(R.id.et_message);
        MyAppcation app = (MyAppcation) getApplication();
        mTuringApiManager = app.getTuringApiManager();
        // turingSDK初始化
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        SDKInitBuilder builder = new SDKInitBuilder(this)
                .setSecret("d257a9b9d5b47d5f").setTuringKey("a9776345bca744c288d8e14a5322939f")
                .setUniqueId(DEVICE_ID);

        SDKInit.init(builder, new InitListener() {
            @Override
            public void onFail(String error) {
                Log.d(TAG, error);
            }

            @Override
            public void onComplete() {
                // 获取userid成功后，才可以请求Turing服务器，需要请求必须在此回调成功，才可正确请求
                mTuringApiManager = new TuringApiManager(getApplicationContext());
                mTuringApiManager.setHttpListener(myHttpConnectionListener);
                bt_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = et_message.getText().toString();
                        if (!message.equals("")) {
                            mTuringApiManager.requestTuringAPI(message);
                            View view = mLayoutInflater.inflate(R.layout.message_item_self,
                                    ll_results, false);
                            TextView textView = (TextView) view.findViewById(R.id.tv_message_self);
                            textView.setText(message);
                            ll_results.addView(view);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                            });
                            et_message.getText().clear();
                        }
                    }
                });
            }
        });

    }

    /**
     * 网络请求回调
     */
    HttpConnectionListener myHttpConnectionListener = new HttpConnectionListener() {

        @Override
        public void onSuccess(RequestResult result) {
            if (result != null) {
                Log.d(TAG, result.getContent().toString());
                Message message = new Gson().fromJson(result.getContent().toString(), Message
                        .class);
                View view = mLayoutInflater.inflate(R.layout.message_item_there,
                        ll_results, false);
                TextView textView = (TextView) view.findViewById(R.id.tv_message_there);
                textView.setText(message.getText());
                ll_results.addView(view);
                String url = message.getUrl();
                if (url != null && !url.equals("")) {
                    View urlview = mLayoutInflater.inflate(R.layout.message_item_there,
                            ll_results, false);
                    TextView tv_url = (TextView) urlview.findViewById(R.id.tv_message_there);
                    tv_url.setTextColor(Color.BLUE);
                    tv_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse("http://www.jb51.net");
                            intent.setData(content_url);
                            startActivity(intent);
                        }
                    });
                    tv_url.setText(message.getUrl());
                    ll_results.addView(urlview);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        }

        @Override
        public void onError(ErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getMessage());
        }
    };
}
