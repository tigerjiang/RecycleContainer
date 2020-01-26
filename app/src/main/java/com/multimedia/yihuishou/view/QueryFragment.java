package com.multimedia.yihuishou.view;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.entity.BaseEntity;
import com.multimedia.yihuishou.entity.CommunityEntity;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.net.NetDataUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryFragment extends BaseFragment {
    private static final String TAG = QueryFragment.class.getSimpleName();
    RecyclerView vUIRecyclerView;
    GeneralAdapter mGeneralAdapter;
    private List<BaseEntity> mData = new ArrayList<>();


    @Override
    protected int setLayoutResId() {
        return R.layout.query_fragment_layout;
    }




    @Override
    public void initFindViews() {
        vUIRecyclerView = (RecyclerView) vContentView.findViewById(R.id.query_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        // 设置布局管理器
        vUIRecyclerView.setLayoutManager(layoutManager);

        // 设置分隔线
        vUIRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(mContext.getResources().getDimensionPixelOffset(R.dimen.dp_13_3), false));
        // 设置增加或删除条目的动画
        vUIRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mGeneralAdapter = new GeneralAdapter(getContext());
        mGeneralAdapter.setOnItemClickListener(new GeneralAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                requestScanQR();
            }
        });
        // 设置Adapter
        vUIRecyclerView.setAdapter(mGeneralAdapter);
        getCommunityData();
    }


    @Override
    public void handleQRcode(String qrString) {
        LogUtils.d(TAG, " handleQRcode "+ qrString);
    }


    //获取积分列表
    private void getCommunityData() {
        NetDataUtils.getInstance().getCommunityTypeList(new NetDataUtils.RequestResultListener<CommunityEntity>() {
            @Override
            public void returnFail(Throwable e) {

            }

            @Override
            public void returnSuccess(List<CommunityEntity> entity) {
                LogUtils.d(TAG,"returnSuccess : "+entity.toString());
                mGeneralAdapter.setData(entity);
            }

            @Override
            public void requestStart() {

            }

        });
    }

}
