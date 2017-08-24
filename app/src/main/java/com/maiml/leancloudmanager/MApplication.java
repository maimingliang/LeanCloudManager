package com.maiml.leancloudmanager;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * 类       名:
 * 说       明:
 * date   2017/8/24
 * author   maimingliang
 */


public class MApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, getString(R.string.leancloud_app_id), getString(R.string.leancloud_app_key));
        AVOSCloud.setDebugLogEnabled(true);

    }
}
