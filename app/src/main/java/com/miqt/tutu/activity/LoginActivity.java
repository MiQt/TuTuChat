package com.miqt.tutu.activity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


import com.miqt.tlchat.tulingchat.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * 一个登录屏幕,提供通过电子邮件/密码登录。
 */
public class LoginActivity extends AbActivity {

    // UI references.
    @ViewInject(R.id.email)
    AutoCompleteTextView username;
    @ViewInject(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);
    }

    @Event(value = R.id.email_sign_in_button, type = OnClickListener.class)
    private void login(View view) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (isNullOrEmpty(user, pass)) {
            Toast.makeText(LoginActivity.this, "用户或密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(user);
        bu2.setPassword(pass);
        bu2.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                Log.d("登录:code=" + e.getErrorCode(), "msg=" + e.getMessage());
                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                //通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
                //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
                //跳转到Mainactivity（主页面）
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 注册按钮被点击，这里使用了Xutils注解
     *
     * @param view
     */
    @Event(value = R.id.tv_regst, type = OnClickListener.class)
    private void onRegsterClick(View view) {
        //跳转到注册界面
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }


}

