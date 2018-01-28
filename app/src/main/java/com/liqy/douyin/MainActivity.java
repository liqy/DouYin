package com.liqy.douyin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liqy.douyin.common.HttpResult;
import com.liqy.douyin.home.HomeApi;
import com.liqy.douyin.network.RetrofitHelper;
import com.liqy.douyin.shoot.ShootApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getShootData();

    }

    private void getShootData() {
        ShootApi api = RetrofitHelper.getShootApi();

        api.musicCollection(0, 1024).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult>() {
                    @Override
                    public void accept(HttpResult result) throws Exception {
                        Log.d(getLocalClassName(), result.toString());
                    }
                });

        api.hotMusic(0, 10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult>() {
                    @Override
                    public void accept(HttpResult result) throws Exception {
                        Log.d(getLocalClassName(), result.toString());
                    }
                });

    }

    public void getHomeData() {

        HomeApi api = RetrofitHelper.getHomeApi();

        api.userInfo("81819485589")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult>() {
                    @Override
                    public void accept(HttpResult s) throws Exception {
                        Log.d(getLocalClassName(), s.toString());
                    }
                });

        api.feed(0, 0, 0, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult>() {
                    @Override
                    public void accept(HttpResult result) throws Exception {
                        Log.d(getLocalClassName(), result.toString());
                    }
                });

        api.commentList("6512401713704471821", 0, 20, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult>() {
                    @Override
                    public void accept(HttpResult result) throws Exception {
                        Log.d(getLocalClassName(), result.toString());
                    }
                });
    }

}
