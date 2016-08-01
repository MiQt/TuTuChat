package com.miqt.tutu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miqt.tlchat.tulingchat.R;
import com.miqt.tutu.entity.UserInfo;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BmobUser userInfo = new BmobUser();
        userInfo.setUsername("123");
        userInfo.setPassword("123");
        //注意：不能用save方法进行注册
        userInfo.signUp(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
    }
}
