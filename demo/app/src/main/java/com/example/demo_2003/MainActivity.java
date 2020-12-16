package com.example.demo_2003;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_2003.factory.Phone;
import com.example.demo_2003.factory.PhoneFactory;
import com.example.demo_2003.factory2.HuaWeiPhoneFactory;
import com.example.demo_2003.factory2.SunmPhoneFactory;
import com.example.demo_2003.factory4.ThreadPoolFactory;
import com.example.demo_2003.single.ThreadPoolManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn_single;
    private Button btn_factory;
    private Button btn_factory2;
    private Button btn_factory3;
    private Disposable subscribe;
    private int i = 5;
    private Button btn_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_single = (Button) findViewById(R.id.btn_single);
        btn_factory = (Button) findViewById(R.id.btn_factory);

        btn_single.setOnClickListener(this);
        btn_factory.setOnClickListener(this);
        btn_factory2 = (Button) findViewById(R.id.btn_factory2);
        btn_factory2.setOnClickListener(this);
        btn_factory3 = (Button) findViewById(R.id.btn_factory3);
        btn_factory3.setOnClickListener(this);


        btn_video = (Button) findViewById(R.id.btn_video);
        btn_video.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_single:
                //单例线程池

                ThreadPoolManager.getmManager().executeTask(runnable);

//                ApiService apiService = RetrofitManager.getmManager().getmApiService();


                break;
            case R.id.btn_factory:
                //工厂模式之简单工厂

                Phone miPhone = PhoneFactory.create("MiPhone");
                miPhone.make();

                Phone iPhone = PhoneFactory.create("IPhone");
                iPhone.make();

                break;
            case R.id.btn_factory2:
                //工厂模式之抽象工厂

                HuaWeiPhoneFactory huaWeiPhoneFactory = new HuaWeiPhoneFactory();
                Phone huaWeiPhone = huaWeiPhoneFactory.createPhone();
                huaWeiPhone.make();

                SunmPhoneFactory sunmPhoneFactory = new SunmPhoneFactory();
                Phone sunmPhone = sunmPhoneFactory.createPhone();
                sunmPhone.make();

                break;
            case R.id.btn_factory3:

                ThreadPoolFactory.getThreadPool(Constant.SCHEDULED_THREADPOOL)
                        .executorTimerTask(new Runnable() {
                            @Override
                            public void run() {

                                if (i > 0) {
                                    Log.e(TAG, "run: " + "倒计时：" + i);
                                }
                                i--;
                            }
                        }, 1, 1, TimeUnit.SECONDS);

                break;
            case R.id.btn_video:

                startActivity(new Intent(MainActivity.this, VideoActivity.class));

                break;
        }
    }

    /**
     * 内部类第二种写法
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO 执行耗时操作

            final Long time = 5L;

            subscribe = Observable.interval(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {

                            if (aLong < time && !subscribe.isDisposed()) {
                                Log.e(TAG, "倒计时：" + (time - aLong - 1));
                            } else {
                                Log.e(TAG, "accept: " + "结束");
                            }
                        }
                    });

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscribe != null) {
            subscribe.dispose();
            subscribe = null;
        }

//        ThreadPoolManager.getmManager().removeTask(runnable);
//
//        ThreadPoolFactory.getThreadPool(Constant.SCHEDULED_THREADPOOL).removeTask();
    }
}