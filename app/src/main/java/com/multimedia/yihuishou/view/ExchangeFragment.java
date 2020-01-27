package com.multimedia.yihuishou.view;

import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.yihuishou.ExchangeDetailActivity;
import com.multimedia.yihuishou.MainActivity;
import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.entity.ProductEntity;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.net.NetDataUtils;
import com.multimedia.yihuishou.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class ExchangeFragment extends BaseFragment {
    private static final String TAG = ExchangeFragment.class.getSimpleName();
    RecyclerView vUIRecyclerView;
    GeneralAdapter mGeneralAdapter;
    private List<ProductEntity> mData = new ArrayList<>();
    private ProductEntity mCurrentProduct;

    @Override
    protected int setLayoutResId() {
        return R.layout.exchange_fragment_layout;
    }

    @Override
    public void initFindViews() {
        vUIRecyclerView = (RecyclerView) vContentView.findViewById(R.id.ex_rv);
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
                mCurrentProduct = mData.get(position);
                requestScanQR();
            }
        });
        // 设置Adapter
        vUIRecyclerView.setAdapter(mGeneralAdapter);
        getProductData();
    }

    @Override
    public void handleQRcode(String qrString) {
        Intent intent = new Intent(getContext(), ExchangeDetailActivity.class);
        intent.putExtra(Constant.TITLE_KEY,getString(R.string.title_exchange));
        intent.putExtra(Constant.CARD_ID_KEY, "xhd123212");
        intent.putExtra(Constant.PRODUCT_KEY, mCurrentProduct);
        intent.putExtra(Constant.USER_KEY, ((MainActivity)getActivity()).getUser());
        startActivity(intent);
        LogUtils.d(TAG, " handleQRcode "+ qrString);
    }


    private void getProductData(){

        NetDataUtils.getInstance().getProductList(new NetDataUtils.RequestResultListener<ProductEntity>() {
            @Override
            public void returnFail(String msg) {

            }

            @Override
            public void returnSuccess(List<ProductEntity> entity) {
                LogUtils.d(TAG,"returnSuccess : "+entity.toString());
                mData = entity;
                mGeneralAdapter.setData(entity);
            }

            @Override
            public void requestStart() {

            }

        });
    }

}
