package com.multimedia.yihuishou;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.entity.UserEntity;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;

import java.util.List;

/**
 * 垃圾回收详情页
 * step 1 扫描居民二维码
 * step 2 显示回收垃圾种类
 * step 3 输入垃圾的重量以及可换取的积分
 * step 4 显示居民剩余积分
 */
public class RecycleDetailActivity  extends BaseActivity {

    private static final String TAG = "RecycleDetailActivity";
    private View mLayoutView;

    private TextView name_tv, weight_tv;
    private EditText weight_et, integral_et;
    private Button submitBtn;
    private RubblishEntity mRubblish ;
    private UserEntity mUser ;
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
               submit();
            }
        });

        weight_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                if(len >= 1) {
                    String content = s.toString().trim();
                    double weight = Double.parseDouble(content);
                    if (isNeedAutoCalculate) {
                        integral_et.setText(weight * mRubblish.getIntegralWeight() + "");
                    }
                }
            }
        });
        return mLayoutView;
    }
    private void initRecycleDetail(Intent intent){
        mRubblish = (RubblishEntity) intent.getSerializableExtra(Constant.RUBBLISH_KEY);
        mUser = (UserEntity) intent.getSerializableExtra(Constant.USER_KEY);
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

        final String cardNo = mCardNo; //居民卡号
        final String comment = TAG; //备注信息
        final String integral = integral_et.getText().toString();;//积分数
        final String inspectorAccount = mUser.getAccount(); //巡检员账号
        final String weight = weight_et.getText().toString();//垃圾重量
        final String rubbishType = mRubblish.getId()+""; //垃圾分类类型1-厨余垃圾； 2-纸张； 3-塑料； 4-易拉罐
        NetDataUtils.getInstance().submitRubblishRecycleInfo(new NetDataUtils.PostResultListener() {
            @Override
            public void returnFail(String  msg) {
                Toast.makeText(RecycleDetailActivity.this, "回收失败 ： " + msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void returnSuccess() {
                Toast.makeText(RecycleDetailActivity.this, "回收成功 ： " , Toast.LENGTH_SHORT).show();
                reQueryResidentInfo();
                finish();
            }

            @Override
            public void requestStart() {

            }
        }, cardNo, comment, inspectorAccount,integral, rubbishType, weight);

    }


}
