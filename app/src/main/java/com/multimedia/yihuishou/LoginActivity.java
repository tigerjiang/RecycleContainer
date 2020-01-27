package com.multimedia.yihuishou;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.multimedia.yihuishou.entity.UserEntity;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;
import com.multimedia.yihuishou.utils.DataUtils;
import com.multimedia.yihuishou.utils.SharePreferenceHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.et_phone)
    EditText mEtUserName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.rpw_checkBox)
    CheckBox rpw_checkBox;

    @BindView(R.id.iv_display)
    CheckBox iv_display;
    @BindView(R.id.loading)
    ProgressBar loading;

    private String userName;
    private String pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        loading.setVisibility(View.GONE);
        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                if (len >= 6 && len <= 16) {
                    mBtnNext.setEnabled(true);
                } else {
                    mBtnNext.setEnabled(false);
                }
            }
        });
        mBtnNext.setOnClickListener(this);
        iv_display.setOnCheckedChangeListener(this);
        rpw_checkBox.setOnCheckedChangeListener(this);

        lognAuto();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                userName = mEtUserName.getText().toString();
                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(LoginActivity.this, " 请输入账号", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, " 请输入密码", Toast.LENGTH_LONG).show();
                }
                login(userName, pwd);
                break;
        }
    }


    private void login(String userName, String pwd) {
        NetDataUtils.getInstance().login(new NetDataUtils.RequestResultListener<UserEntity>() {
            @Override
            public void returnFail(String e) {
                loading.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, " 登录失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void returnSuccess(List<UserEntity> entity) {
                loading.setVisibility(View.GONE);
                UserEntity userEntity = entity.get(0);
                if (userEntity == null) {
                    Toast.makeText(LoginActivity.this, " 登录失败", Toast.LENGTH_LONG).show();
                } else {
                    SharePreferenceHelper.saveSharedPreference(Constant.USERNAME_KEY, userName);
                    if(rpw_checkBox.isChecked()){
                        SharePreferenceHelper.saveSharedPreference(Constant.PWD_KEY, pwd);
                    }
                    DataUtils.storeUser(userName, userEntity);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(Constant.USER_KEY, userEntity);
                    startActivity(intent);
                }
            }

            @Override
            public void requestStart() {
                loading.setVisibility(View.VISIBLE);
            }
        }, userName, pwd);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.rpw_checkBox) {
            if (b) {
                SharePreferenceHelper.saveSharedPreference(Constant.PWD_KEY, et_pwd.getText());
            } else {
                SharePreferenceHelper.remove(Constant.PWD_KEY);
            }
        } else if (compoundButton.getId() == R.id.iv_display) {
            if (b) {
                et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

    }


    private boolean checkAutoLogin(){
        pwd = (String) SharePreferenceHelper.getSharedPreference(Constant.PWD_KEY, null);
        userName = (String) SharePreferenceHelper.getSharedPreference(Constant.USERNAME_KEY, null);
        if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(userName)){
            rpw_checkBox.setChecked(true);
            return true;
        }
        return false;
    }

    private void lognAuto(){
        if(checkAutoLogin()){
            mEtUserName.setText(userName);
            et_pwd.setText(pwd);
            login(userName,pwd);
        }

    }
}
