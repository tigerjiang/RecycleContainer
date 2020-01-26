package com.multimedia.yihuishou.view.impl;

/**
 * Created by wuchao5 on 18-7-31.
 */

public interface IAccountResultCallback {

    void onSuccess(int channel, String Msg);

    void onFailed(int errorCode, int channel, String Msg);
}
