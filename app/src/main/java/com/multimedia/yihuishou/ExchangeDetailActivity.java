package com.multimedia.yihuishou;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.multimedia.yihuishou.entity.ProductEntity;
import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.entity.UserEntity;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;

import java.util.List;

/**
 * 垃圾回收详情页
 * step 1 扫描居民二维码
 * step 2 显示兑换商品种类
 * step 3 输入商品的数量以及所需积分
 * step 4 显示居民剩余积分
 */
public class ExchangeDetailActivity extends BaseActivity {
    private View mLayoutView;
    private static final String TAG = "ExchangeDetailActivity";

    private TextView name_tv, unit_tv, integral_et;
    private EditText weight_et;
    private Button submitBtn;
    private ProductEntity mProduct ;
    private UserEntity mUser ;
    private int totalIntegral;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getIntent());
        initProductDetail(getIntent());
    }

    @Override
    public View getContainView() {
        mLayoutView = LayoutInflater.from(this).inflate(R.layout.exchange_detil_layout, null);
        name_tv = mLayoutView.findViewById(R.id.name_tv);
        unit_tv = mLayoutView.findViewById(R.id.unit_tv);
        integral_et = mLayoutView.findViewById(R.id.integral_et);
        weight_et = mLayoutView.findViewById(R.id.weight_et);
        submitBtn = mLayoutView.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalIntegral > Integer.parseInt(mResidentEntity.getIntegral())){
                    Toast.makeText(ExchangeDetailActivity.this,"积分不够兑换该产品",Toast.LENGTH_LONG).show();
                }else {
                    submit();
                }
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
                    integral_et.setText(weight * mProduct.getPrice()+ "");
                    totalIntegral = (int)(weight * mProduct.getPrice());
                }
            }
        });
        return mLayoutView;
    }
    private void initProductDetail(Intent intent){
        mProduct = (ProductEntity) intent.getSerializableExtra(Constant.PRODUCT_KEY);
        mUser = (UserEntity) intent.getSerializableExtra(Constant.USER_KEY);
        name_tv.setText(mProduct.getName());
        unit_tv.setText(mProduct.getUnit());
    }


    private void submit(){

        final String cardNo = mCardNo; //居民卡号
        final String comment = TAG; //备注信息
        final String count = weight_et.getText().toString();;//兑换的商品数量
        final String inspectorAccount = mUser.getAccount(); //巡检员账号
        final String productId = mProduct.getId()+""; //商品ID
        LogUtils.d(TAG," submit : "+ cardNo + " - "+ comment+ " - "+ count + " - "+ inspectorAccount + " - "+productId);
        NetDataUtils.getInstance().submitConsumeInfo(new NetDataUtils.PostResultListener() {
            @Override
            public void returnFail(String  msg) {
                Toast.makeText(ExchangeDetailActivity.this, "兑换失败 ： " + msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void returnSuccess() {
                Toast.makeText(ExchangeDetailActivity.this, "兑换成功", Toast.LENGTH_SHORT).show();
                reQueryResidentInfo();
                finish();
            }

            @Override
            public void requestStart() {

            }
        },cardNo,comment,count,inspectorAccount,productId);

    }

}
