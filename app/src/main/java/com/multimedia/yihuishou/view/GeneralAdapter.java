package com.multimedia.yihuishou.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.entity.BaseEntity;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.utils.imageloader.ImgUtils;
import com.multimedia.yihuishou.view.ui.UIImageView;

import java.util.ArrayList;
import java.util.List;

public class GeneralAdapter<T> extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {

    private static final String TAG = GeneralAdapter.class.getSimpleName();

    //当前上下文对象
    Context context;

    /**
     * 数据列表
     */
    protected List<T> mList;

    /**
     * 是否显示底部UI
     */
    private boolean isShowFooter;

    public GeneralAdapter(Context context) {
        this.context = context;
        isShowFooter = false;
    }

    public void setData(List<T> dataList){
        LogUtils.d(TAG,"setData : "+ dataList);
        mList = dataList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GeneralAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View containerView = View.inflate(context, R.layout.ui_card_icon_title_desc, null);
        return new MyViewHolder(containerView);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralAdapter.MyViewHolder holder, int position) {
        BaseEntity entity =(BaseEntity) mList.get(position);
        onUIRefresh(holder,position,entity);

    }

    private void onUIRefresh(MyViewHolder holder , int position, BaseEntity entity){
        LogUtils.d(TAG," entity : "+ entity.getTitle() + " - "+entity.getSubtitle() + " - "+entity.getDesc());
        if (entity!=null) {
            ImgUtils.load(holder.vUIIcon, entity.getUrl(), R.drawable.gray_default);
            holder.vTitle.setText(entity.getTitle());
            holder.vSubTitle.setText(entity.getSubtitle());
            holder.vDesc.setText(entity.getDesc());
//            if (entity.isChecked()) {
//                holder.vLayout.setSelected(true);
//                holder.vTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gray_default, 0, 0, 0);
//            } else {
//                holder.vLayout.setSelected(false);
//                holder.vTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//            }

            holder.vLayout.setTag(entity);
            holder.vLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener!=null){
                        mItemClickListener.onItemClickListener(position);
                    }
                }
            });
        } else {
            holder. vTitle.setText("");
            holder. vSubTitle.setText("");
            holder.vDesc.setText("");
            holder.vLayout.setSelected(false);
            holder. vTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }


    public void setOnItemClickListener(OnItemClickListener l){
        mItemClickListener = l;
    }

    private OnItemClickListener mItemClickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout vLayout;
        protected UIImageView vUIIcon;
        protected TextView vTitle;
        protected TextView vSubTitle;
        protected TextView vDesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            vLayout = (RelativeLayout) itemView.findViewById(R.id.v_layout);
            vUIIcon = (UIImageView) itemView.findViewById(R.id.ui_icon);
            vTitle = (TextView) itemView.findViewById(R.id.v_title);
            vSubTitle = (TextView) itemView.findViewById(R.id.v_subtitle);
            vDesc = (TextView) itemView.findViewById(R.id.v_desc);
        }

    }

}
