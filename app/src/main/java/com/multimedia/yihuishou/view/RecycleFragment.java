package com.multimedia.yihuishou.view;

import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.yihuishou.MainActivity;
import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.RecycleDetailActivity;
import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class RecycleFragment extends BaseFragment {
    private static final String TAG = RecycleFragment.class.getSimpleName();
    //    UIRecyclerView vUIRecyclerView;
    RecyclerView vUIRecyclerView;
    GeneralAdapter mGeneralAdapter;
    private List<RubblishEntity> mData = new ArrayList<>();
    private RubblishEntity mCurrentRubblish;

    @Override
    protected int setLayoutResId() {
        return R.layout.recycle_fragment_layout;
    }


    @Override
    public void initFindViews() {
        vUIRecyclerView = (RecyclerView) vContentView.findViewById(R.id.ry_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        // 设置布局管理器
        vUIRecyclerView.setLayoutManager(layoutManager);

        RecycleViewDivider itemDivider = new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 2,
                getResources().getColor(R.color.dividerColor));
        itemDivider.setDrawLastItem(false);
        // 设置分隔线
        vUIRecyclerView.addItemDecoration(itemDivider);
        // 设置增加或删除条目的动画
        vUIRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mGeneralAdapter = new GeneralAdapter(getContext());
        mGeneralAdapter.setOnItemClickListener(new GeneralAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                mCurrentRubblish = mData.get(position);
                requestScanQR();
            }
        });
        // 设置Adapter;
        vUIRecyclerView.setAdapter(mGeneralAdapter);
        getRubblishData();
    }


    @Override
    public void handleQRcode(String qrString) {

        Intent intent = new Intent(getContext(), RecycleDetailActivity.class);
        intent.putExtra(Constant.TITLE_KEY,getString(R.string.title_recycle));
        intent.putExtra(Constant.CARD_ID_KEY, "xhd123212");
        intent.putExtra(Constant.RUBBLISH_KEY, mCurrentRubblish);
        intent.putExtra(Constant.USER_KEY, ((MainActivity)getActivity()).getUser());
        startActivity(intent);
        LogUtils.d(TAG, " handleQRcode " + qrString);
    }


    private void getRubblishData() {
        NetDataUtils.getInstance().
                getRubblishTypeList(new NetDataUtils.RequestResultListener<RubblishEntity>() {
                    @Override
                    public void returnFail(String msg) {

                    }

                    @Override
                    public void returnSuccess(List<RubblishEntity> entity) {
                        LogUtils.d(TAG, "returnSuccess : " + entity.toString());
                        mData.clear();
                        mData.addAll(entity);
                        mGeneralAdapter.setData(entity);
                    }


                    @Override
                    public void requestStart() {

                    }

                });
    }
}
