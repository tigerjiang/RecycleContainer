package com.multimedia.yihuishou.net;

import com.multimedia.yihuishou.entity.CommunityEntity;
import com.multimedia.yihuishou.entity.ConsumeRecordEntity;
import com.multimedia.yihuishou.entity.ModelBase;
import com.multimedia.yihuishou.entity.ProductEntity;
import com.multimedia.yihuishou.entity.RecycleRecordEntity;
import com.multimedia.yihuishou.entity.ResidentEntity;
import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.entity.UserEntity;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroDataApi {
    //查询全部社区列表
    @GET("getCommunityTypeList")
    Observable<ModelBase<CommunityEntity>> getCommunityTypeList();

    //获取消费记录信息
    @GET("getConsumeRecordDetailInfo")
    Observable<ModelBase<ConsumeRecordEntity>> getConsumeRecordDetailInfo();

    //获取兑换商品
    @GET("getProductList")
    Observable<ModelBase<ProductEntity>> getProductList();

    //根据卡号获取居民垃圾回收记录信息
    @GET("getRecycleRecordDetailInfo")
    Observable<ModelBase<RecycleRecordEntity>> getRecycleRecordDetailInfo(@Query("cardNo") String cardNo);

    //获取居民信息
    @GET("getResidentInfo")
    Observable<ModelBase<ResidentEntity>> getResidentInfo(@Query("cardNo") String cardNo);


    //获取垃圾分类
    @GET("getCommunityTypeList")
    Observable<ModelBase<RubblishEntity>> getRubblishTypeList();


    //获取用户列表
    @GET("getProductList")
    Observable<ModelBase<UserEntity>> getUserList();


}
