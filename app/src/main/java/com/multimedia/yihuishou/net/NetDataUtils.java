package com.multimedia.yihuishou.net;


import com.multimedia.yihuishou.entity.BaseEntity;
import com.multimedia.yihuishou.entity.CommunityEntity;
import com.multimedia.yihuishou.entity.ModelBase;
import com.multimedia.yihuishou.entity.ProductEntity;
import com.multimedia.yihuishou.entity.ResidentEntity;
import com.multimedia.yihuishou.entity.RubblishEntity;
import com.multimedia.yihuishou.entity.UserEntity;
import com.multimedia.yihuishou.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NetDataUtils {
    private static final String TAG = NetDataUtils.class.getSimpleName();
    public interface RequestResultListener<T>{
        void returnFail(String e);

        void returnSuccess(List<T> entity);

        void requestStart();

    }


    public interface PostResultListener{
        void returnFail(String e);

        void returnSuccess();

        void requestStart();

    }

    public static NetDataUtils getInstance(

    ) {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        public static final NetDataUtils sInstance = new NetDataUtils();
    }

    //获取社区列表
    public void getCommunityTypeList(final RequestResultListener resultListener) {
        final List<CommunityEntity> dataList = new ArrayList<>();
        Observable<ModelBase<CommunityEntity>>  observable = RetroApiService.create(RetroDataApi.class)
                .getCommunityTypeList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<CommunityEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase<CommunityEntity> entityModelBase) {
                        LogUtils.d(TAG, "onNext : ");
                        if(entityModelBase.getResult() == 0){
                            //成功
                            dataList.addAll(entityModelBase.getData());
                            resultListener.returnSuccess(dataList);
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }


   //获取兑换商品
    public void getProductList(final RequestResultListener resultListener) {
        final List<ProductEntity> dataList = new ArrayList<>();
        Observable<ModelBase<ProductEntity>>  observable = RetroApiService.create(RetroDataApi.class)
                .getProductList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<ProductEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase<ProductEntity> entityModelBase) {
                        LogUtils.d(TAG, "onNext : ");
                        if(entityModelBase.getResult() == 0){
                            //成功
                            dataList.addAll(entityModelBase.getData());
                            resultListener.returnSuccess(dataList);
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }



    //获取垃圾分类
    public void getRubblishTypeList(final RequestResultListener resultListener) {
        final List<RubblishEntity> dataList = new ArrayList<>();
        Observable<ModelBase<RubblishEntity>>  observable = RetroApiService.create(RetroDataApi.class)
                .getRubblishTypeList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<RubblishEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase<RubblishEntity> entityModelBase) {
                        LogUtils.d(TAG, "onNext : " +entityModelBase.getData());
                        if(entityModelBase.getResult() == 0){
                            //成功
                            dataList.addAll(entityModelBase.getData());
                            resultListener.returnSuccess(dataList);
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }


    //获取居民信息
    public void getResidentInfo(final RequestResultListener resultListener, String cardId) {
        final List<ResidentEntity> dataList = new ArrayList<>();
        Observable<ModelBase<ResidentEntity>>  observable = RetroApiService.create(RetroDataApi.class)
                .getResidentInfo(cardId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<ResidentEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase<ResidentEntity> entityModelBase) {
                        LogUtils.d(TAG, "onNext : " +entityModelBase.getData());
                        if(entityModelBase.getResult() == 0){
                            //成功
                            dataList.addAll(entityModelBase.getData());
                            resultListener.returnSuccess(dataList);
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");

                    }
                });
    }


    //用户登录
    public void login(final RequestResultListener resultListener, String userName, String pwd) {
        final List<UserEntity> dataList = new ArrayList<>();
        Observable<ModelBase<UserEntity>>  observable = RetroApiService.create(RetroDataApi.class)
                .userLogin(userName,pwd);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase<UserEntity> entityModelBase) {
                        LogUtils.d(TAG, "onNext : " +entityModelBase.getData());
                        if(entityModelBase.getResult() == 0){
                            //成功
                            dataList.addAll(entityModelBase.getData());
                            resultListener.returnSuccess(dataList);
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }

    //垃圾回收提交
    public void submitRubblishRecycleInfo(final PostResultListener resultListener, String cardNO, String comment, String inspectorAccount,String integral,String rubbishType,String weight) {
        Observable<ModelBase>  observable = RetroApiService.create(RetroDataApi.class)
                .submitRubblishRecycleInfo(cardNO,comment,inspectorAccount,integral,rubbishType,weight);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase entityModelBase) {
                        if(entityModelBase.getResult() == 0){
                            resultListener.returnSuccess();
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }


    //提交消费信息
    public void submitConsumeInfo(final PostResultListener resultListener, String cardNO, String comment,
                                          String count,String inspectorAccount,String productId) {
        Observable<ModelBase>  observable = RetroApiService.create(RetroDataApi.class)
                .submitConsumeInfo(cardNO,comment,count,inspectorAccount,productId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                        if (resultListener != null) {
                            resultListener.requestStart();
                        }
                    }

                    @Override
                    public void onNext(ModelBase entityModelBase) {
                        LogUtils.d(TAG, "onNext : " +entityModelBase.toString());
                        if(entityModelBase.getResult() == 0){
                            resultListener.returnSuccess();
                        }else{
                            //失败
                            resultListener.returnFail(entityModelBase.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError : ", e);
                        if (resultListener != null) {
                            resultListener.returnFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete : ");


                    }
                });
    }

}
