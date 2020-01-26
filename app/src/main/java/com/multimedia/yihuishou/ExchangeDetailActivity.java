package com.multimedia.yihuishou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 垃圾回收详情页
 * step 1 扫描居民二维码
 * step 2 显示兑换商品种类
 * step 3 输入商品的数量以及所需积分
 * step 4 显示居民剩余积分
 */
public class ExchangeDetailActivity extends BaseActivity {
    private View mLayoutView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getIntent());
    }

    @Override
    public View getContainView() {
        mLayoutView = LayoutInflater.from(this).inflate(R.layout.exchange_detil_layout, null);
        return mLayoutView;
    }

    private void initDetailData(){

    }
}
