package com.multimedia.yihuishou.view;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ExchangeFragment extends BaseFragment {
    private static final String TAG = ExchangeFragment.class.getSimpleName();
    RecyclerView mRecycleView;
    private List<String > mData = new ArrayList<>();
    @Override
    protected int setLayoutResId() {
        return R.layout.exchange_fragment_layout;
    }

    @Override
    public void initFindViews() {
        mRecycleView = vContentView.findViewById(R.id.ex_rv);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        GeneralAdapter generalAdapter = new GeneralAdapter(getContext(),generateData());
        generalAdapter.setOnItemClickListener(new GeneralAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.d(TAG," data : "+ position + " - "+mData.get(position));
            }
        });

      mRecycleView.setAdapter(generalAdapter);
    }


    private List<String> generateData(){
        mData.add("haha");
        mData.add("haha");
        mData.add("haha");
        mData.add("aa");
        mData.add("haha");
        mData.add("haha");
        mData.add("aa");
        mData.add("haha");
        return mData;
    }
}
