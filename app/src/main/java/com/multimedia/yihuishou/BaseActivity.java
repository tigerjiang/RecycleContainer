package com.multimedia.yihuishou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.multimedia.yihuishou.entity.ResidentEntity;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;
import com.multimedia.yihuishou.utils.DataUtils;
import com.multimedia.yihuishou.utils.imageloader.ImgUtils;
import com.multimedia.yihuishou.view.ui.UIImageView;

import java.util.List;


public abstract class BaseActivity  extends AppCompatActivity {

    private ViewGroup mContainerView ;
    private TextView mTitleView, mNameView, mAddressView, mIntegralView;
    private ImageButton mBackView;
    private UIImageView mHeadIcon;
    protected String mCardId;
    protected ResidentEntity mResidentEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        mContainerView = findViewById(R.id.container);
        mTitleView = findViewById(R.id.title);
        mHeadIcon = findViewById(R.id.head_icon);
        mNameView = findViewById(R.id.name);
        mAddressView = findViewById(R.id.address);
        mIntegralView = findViewById(R.id.integral);
        mBackView = findViewById(R.id.back);
        mContainerView.addView(getContainView());
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void initData(Intent intent){
        String title = intent.getStringExtra(Constant.TITLE_KEY);
        mCardId = intent.getStringExtra(Constant.CARD_ID_KEY);
        mResidentEntity = DataUtils.getCacheResident(mCardId);
        if(mResidentEntity == null){
            getResidentInfo(mRequestResultListener,mCardId);
        }else{
            updateResidentInfo(mResidentEntity);
        }

        mTitleView.setText(title);
    }

    //子类view
    public  abstract View getContainView();


    private NetDataUtils.RequestResultListener mRequestResultListener = new NetDataUtils.RequestResultListener<ResidentEntity>(){

        @Override
        public void returnFail(Throwable e) {

        }

        @Override
        public void returnSuccess(List<ResidentEntity> entity) {
            final ResidentEntity residentEntity= entity.get(0);
            updateResidentInfo(residentEntity);
        }

        @Override
        public void requestStart() {

        }
    };

   private void getResidentInfo(NetDataUtils.RequestResultListener listener, String cardId){
       NetDataUtils.getInstance().getResidentInfo(listener,cardId);
    }

    private void updateResidentInfo(ResidentEntity residentEntity){
        ImgUtils.load(mHeadIcon, residentEntity.getUrl(), R.drawable.gray_default);
        mNameView.setText(residentEntity.getName());
        mAddressView.setText(residentEntity.getAddress());
        mIntegralView.setText(" "+residentEntity.getIntegral());
    }
}
