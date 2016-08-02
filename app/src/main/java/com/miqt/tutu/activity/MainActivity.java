package com.miqt.tutu.activity;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miqt.tlchat.tulingchat.R;
import com.miqt.tutu.app.MyAppcation;
import com.turing.androidsdk.TuringApiManager;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
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
        mTuringApiManager.setHttpListener(myHttpConnectionListener);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = et_message.getText().toString();
                if (!message.equals("")) {
                    mTuringApiManager.requestTuringAPI(message);
                }
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
                tv_results.setText(result.getContent().toString());
            }
        }

        @Override
        public void onError(ErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getMessage());
        }
    };
}
