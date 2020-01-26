package com.multimedia.yihuishou.view.impl;

import android.view.View;

/**
 * Created by weiyupei on 2018/10/31.
 */

public interface IUpdatePlayPop {

    int TV_PROGRAM_MENU = 1;

    int TV_SELECT_CHANNEL = 2;

    /**
     * 建立POP视图
     * @return
     */
    public View createPlayPop(int popType);

    //目前该接口只有在播放电视直播内容时，会用到该接口
    //全屏播放时，弹出右侧弹窗，更新弹窗中的UI，如节目单弹窗、选台弹窗
    public void updatePlayPop(int popType);


}
