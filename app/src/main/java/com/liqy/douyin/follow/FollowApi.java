package com.liqy.douyin.follow;

import com.liqy.douyin.common.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 关注页面
 * Created by liqy on 2018/1/28.
 */

public interface FollowApi {

    /**
     * 关注用户
     *
     * @param count        1000
     * @param with_fstatus 1
     * @param max_time
     * @param min_time     0
     * @return
     */
    @GET("aweme/v1/spotlight/relation/")
    Observable<HttpResult> spotlightRelation(
            @Query("count") int count,
            @Query("with_fstatus") int with_fstatus,
            @Query("max_time") long max_time,
            @Query("min_time") long min_time
    );

    /**
     * 关注用户Feed列表
     *
     * @param max_cursor 0
     * @param min_cursor 0
     * @param count      20
     * @param pull_type  0
     * @param feed_style 1
     * @return
     */
    @GET("aweme/v1/follow/feed/")
    Observable<HttpResult> followFeed(@Query("max_cursor") int max_cursor,
                                      @Query("min_cursor") int min_cursor,
                                      @Query("count") int count,
                                      @Query("pull_type") int pull_type,
                                      @Query("feed_style") int feed_style);
}
