package com.example.demo_2003.single;

import com.example.demo_2003.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit单例封装
 */
public class RetrofitManager {

    private static RetrofitManager mManager;
    private ApiService mApiService;

    private RetrofitManager() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        //读写超时
        okBuilder.readTimeout(60, TimeUnit.SECONDS);
        //连接超时
        okBuilder.connectTimeout(60, TimeUnit.SECONDS);
//      TODO 添加OK拦截器  okBuilder.addInterceptor();
        mApiService = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okBuilder.build())
                .build()
                .create(ApiService.class);
    }

    public static RetrofitManager getmManager() {
        if (mManager == null) {
            synchronized (RetrofitManager.class) {
                if (mManager == null) {
                    mManager = new RetrofitManager();
                }
            }
        }
        return mManager;
    }

    public ApiService getmApiService() {
        return mApiService;
    }
}
