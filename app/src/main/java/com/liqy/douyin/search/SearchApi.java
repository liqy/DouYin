package com.liqy.douyin.search;

import com.liqy.douyin.common.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 搜索
 * Created by liqy on 2018/1/28.
 */

public interface SearchApi {

    /**
     * 热搜
     *
     * @return
     */
    @GET("aweme/v1/hot/search/")
    Observable<HttpResult> hot();


    /**
     * 发现更多
     *
     * @return
     */
    @GET("aweme/v1/find/")
    Observable<HttpResult> find();


    /**
     * 推荐用户列表
     *
     * @param type
     * @return
     */
    @GET("aweme/v1/recommend/user/")
    Observable<HttpResult> recommendUser(@Query("type") int type);

    /**
     * 类型列表
     *
     * @param cursor 索引
     * @param count  5
     * @return
     */
    @GET("aweme/v1/category/list/")
    Observable<HttpResult> categoryList(@Query("cursor") int cursor,
                                        @Query("count") int count);


    /**
     * 搜索发现用户模块
     *
     * @param cursor
     * @param keyword
     * @param count   10
     * @param type    1
     * @return
     */
    @GET("aweme/v1/discover/search/")
    Observable<HttpResult> discover(@Query("cursor") int cursor,
                                    @Query("keyword") String keyword,
                                    @Query("count") int count,
                                    @Query("type") int type);

    /**
     * 搜索发现挑战模块
     *
     * @param max_cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/recommend/challenge/")
    Observable<HttpResult> challenge(
            @Query("max_cursor") int max_cursor,
            @Query("count") int count
    );

    /**
     * 搜索发现用户模块
     *
     * @param cursor
     * @param keyword
     * @param count
     * @return
     */
    @GET("aweme/v1/challenge/search/")
    Observable<HttpResult> discoverChallenge(@Query("cursor") int cursor,
                                             @Query("keyword") String keyword,
                                             @Query("count") int count);


    /**
     * 搜索发现音乐模块
     *
     * @param cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/music/rank/")
    Observable<HttpResult> rank(@Query("cursor") int cursor,
                                @Query("count") int count);

    /**
     * 音乐搜索关键字
     *
     * @param cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/music/search/")
    Observable<HttpResult> musicSearch(@Query("cursor") int cursor,
                                       @Query("keyword") String keyword,
                                       @Query("count") int count);


}
