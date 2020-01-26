package com.multimedia.yihuishou.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean mIsVertical;

    public LinearLayoutItemDecoration(int space, boolean isVertical) {
        this.space = space;
        mIsVertical = isVertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if (mIsVertical) {
            if (parent.getChildPosition(view) != 0) {
                outRect.top = space;
            }
        } else {
            if (parent.getChildPosition(view) != 0) {
                outRect.left = space;
            }
        }


    }
}
