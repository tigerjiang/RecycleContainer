package com.multimedia.yihuishou;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.multimedia.yihuishou.entity.BaseEntity;
import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.utils.Constant;

/**
 * 垃圾回收详情页
 * step 1 扫描居民二维码
 * step 2 显示回收垃圾种类
 * step 3 输入垃圾的重量以及可换取的积分
 * step 4 显示居民剩余积分
 */
public class RecycleDetailActivity  extends BaseActivity {

    private View mLayoutView;

    private TextView name_tv, weight_tv;
    private EditText weight_et, integral_et;
    private Button submitBtn;
    private RubblishEntity mRubblish ;
    private boolean isNeedAutoCalculate= false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getIntent());
        initRecycleDetail(getIntent());
    }

    @Override
    public View getContainView() {
        mLayoutView = LayoutInflater.from(this).inflate(R.layout.recycle_detail_layout, null);
        name_tv = mLayoutView.findViewById(R.id.name_tv);
        weight_tv = mLayoutView.findViewById(R.id.weight_tv);
        weight_et = mLayoutView.findViewById(R.id.weight_et);
        integral_et = mLayoutView.findViewById(R.id.integral_et);
        submitBtn = mLayoutView.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        weight_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        return mLayoutView;
    }
    private void initRecycleDetail(Intent intent){
        mRubblish = (RubblishEntity) intent.getSerializableExtra(Constant.RUBBLISH_KEY);
        name_tv.setText(mRubblish.getName());
        isNeedAutoCalculate = false;
        //按次计算
        if (mRubblish.getIntegralType().equals(Constant.CZD_KEY)) {

            weight_tv.setText("次");
            weight_et.setText("1");
            weight_et.setEnabled(false);
            //重量自动计算
        } else if (mRubblish.getIntegralType().equals(Constant.WZD_KEY)) {
            weight_tv.setText("千克");
            isNeedAutoCalculate = true;
            //重量手动计算
        } else if (mRubblish.getIntegralType().equals(Constant.WSD_KEY)) {
            weight_tv.setText("千克");
            isNeedAutoCalculate = false;
            //重量手动计算
        }
    }


    private void submit(){

    }


}
