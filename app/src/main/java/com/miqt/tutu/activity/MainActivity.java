package com.miqt.tutu.activity;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miqt.tlchat.tulingchat.R;
import com.miqt.tutu.app.MyAppcation;
import com.miqt.tutu.entity.Message;
import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity，fdssaff";
    private TuringApiManager mTuringApiManager;

    Button bt_send;
    TextView tv_results;
    EditText et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_send = (Button) findViewById(R.id.bt_send);
        tv_results = (TextView) findViewById(R.id.tv_results);
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
                            tv_results.append("\n[我]" + message);
                            mTuringApiManager.requestTuringAPI(message);
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
                tv_results.append("\n[果果]" + message.getText());
            }
        }

        @Override
        public void onError(ErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getMessage());
        }
    };
}
