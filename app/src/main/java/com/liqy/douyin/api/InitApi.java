package com.liqy.douyin.api;

import com.liqy.douyin.common.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 初始全局设置
 * Created by liqy on 2018/1/28.
 */

public interface InitApi {

    /**
     * 全局参数设置
     * @return
     */
    @GET("aweme/v1/settings/")
    Observable<HttpResult> settings();

    /**
     * AB测试
     * @return
     */
    @GET("aweme/v1/abtest/param/")
    Observable<HttpResult> abtest();


}
