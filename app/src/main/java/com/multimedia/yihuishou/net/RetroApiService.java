package com.multimedia.yihuishou.net;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroApiService {
    private static final String BASE_URL = "http://47.105.51.4:8066/api/";



    Retrofit.Builder builder = new Retrofit.Builder();
    Retrofit retrofit = builder.build();

    public static Retrofit.Builder getRetrofitBuilder() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        retrofitBuilder.baseUrl(BASE_URL)       //添加url
                .addConverterFactory(GsonConverterFactory.create())   //添加gson自动解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return retrofitBuilder;
    }

    public static <T> T create(final Class<T> service) {
        Retrofit.Builder builder = getRetrofitBuilder();
        Retrofit retrofit = builder.build();
        return (T) retrofit.create(service);
    }
}
