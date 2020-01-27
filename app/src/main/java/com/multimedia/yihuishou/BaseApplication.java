package com.multimedia.yihuishou;

import android.app.Application;

import com.multimedia.yihuishou.utils.SharePreferenceHelper;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharePreferenceHelper.initSp(getApplicationContext());
    }
}
