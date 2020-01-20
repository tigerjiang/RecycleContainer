package com.multimedia.yihuishou.net;


import com.multimedia.yihuishou.entity.CommunityEntity;
import com.multimedia.yihuishou.entity.ModelBase;
import com.multimedia.yihuishou.log.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NetDataUtils {
    private static final String TAG = NetDataUtils.class.getSimpleName();
    private Observable<ModelBase<CommunityEntity>> observable;

    public interface RequestResultListener {
        void returnFail(Throwable e);

        void returnSuccess();

        void parseData(ModelBase<CommunityEntity> T);
    }

    public static NetDataUtils getInstance(

    ) {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        public static final NetDataUtils sInstance = new NetDataUtils();
    }

    public void getCommunityTypeList(final  RequestResultListener resultListener) {
        observable = RetroApiService.create(RetroDataApi.class)
                .getCommunityTypeList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelBase<CommunityEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(ModelBase<CommunityEntity> entityModelBase) {
                        LogUtils.d(TAG,"onNext : ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG,"onError : " ,e);
                        if (resultListener != null) {
                            resultListener.returnFail(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG,"onComplete : ");
                        if (resultListener != null) {
                            resultListener.returnSuccess();
                        }

                    }
                });
    }

    public void onDestory(){
        observable = null;
    }
}
