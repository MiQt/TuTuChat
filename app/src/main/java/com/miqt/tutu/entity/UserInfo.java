package com.miqt.tutu.entity;

import com.miqt.tutu.activity.StartActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by IEZ on 2016/8/1.
 */
public class UserInfo extends BmobUser {
    String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
