package com.liqy.douyin;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.liqy.douyin.common.BaseActivity;
import com.liqy.douyin.common.HttpResult;
import com.liqy.douyin.db.DBHelper;
import com.liqy.douyin.db.UserDao;
import com.liqy.douyin.entity.User;
import com.liqy.douyin.entity.UserResult;
import com.liqy.douyin.home.HomeApi;
import com.liqy.douyin.network.Constants;
import com.liqy.douyin.network.RetrofitHelper;
import com.liqy.douyin.shoot.ShootApi;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;
import com.uuch.adlibrary.AdConstant;
import com.uuch.adlibrary.AdManager;
import com.uuch.adlibrary.bean.AdInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 运行时权限：https://github.com/tbruyelle/RxPermissions
 */
public class MainActivity extends BaseActivity {

    private List<AdInfo> advList = null;
    AgentWeb mAgentWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //当用户使用自有账号登录时，可以这样统计：
        MobclickAgent.onProfileSignIn(Constants.DEVICE_ID);

        getShootData();

        getHomeData();

        /**
         * 自定义统计事件
         */

        MobclickAgent.onEvent(this, "add_cart");

        initRuntime();

        TextView hello = (TextView) findViewById(R.id.hello);
        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });
    }

    /**
     * 初始化对话框
     */
    private void initDialog() {
        advList = new ArrayList<>();
        AdInfo adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage1.png");
        advList.add(adInfo);

        adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png");
        adInfo.setUrl("https://www.baidu.com/");
        advList.add(adInfo);

        AdManager adManager = new AdManager(MainActivity.this, advList);

        adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
            @Override
            public void onImageClick(View view, final AdInfo advInfo) {

                if (!TextUtils.isEmpty(advInfo.getUrl())) {
                    WebActivity.openWeb(MainActivity.this, advInfo);
                }

                Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
            }
        });
        adManager.showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
    }

    private void initRuntime() {
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance

        // Must be done during an initialization phase like onCreate
        rxPermissions
                .requestEach(Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                        if (permission.granted) {
                            // `permission.name` is granted !

                            Log.d(getLocalClassName(), permission.name);

                            if (TextUtils.equals(permission.name, Manifest.permission.CAMERA)) {
                                //TODO
                            }

                            if (TextUtils.equals(permission.name, Manifest.permission.READ_PHONE_STATE)) {
                                //TODO
                            }

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                        }


                    }
                });
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
                .subscribe(new Consumer<UserResult<User>>() {
                    @Override
                    public void accept(UserResult<User> result) throws Exception {
                        Log.d(getLocalClassName(), result.toString());
                        UserDao dao= DBHelper.getDaoSession().getUserDao();

                        //保存数据
                        dao.save(result.user);

                        dao.count();//表中数据的列数

//                        dao.delete(); 删除必须确认主键

//                        dao.queryBuilder() 构建查询语句

//                        dao.queryRaw() 自己跟需要编写SQL语句


                    }
                });

//        api.feed(0, 0, 0, 6)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<HttpResult>() {
//                    @Override
//                    public void accept(HttpResult result) throws Exception {
//                        Log.d(getLocalClassName(), result.toString());
//                    }
//                });
//
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

    @Override
    public String getPageStartName() {
        return getLocalClassName();
    }

    @Override
    public String getPageEndName() {
        return getLocalClassName();
    }
}
