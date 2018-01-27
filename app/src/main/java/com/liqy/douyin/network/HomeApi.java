package com.liqy.douyin.network;

import com.liqy.douyin.common.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liqy on 2018/1/27.
 */

public interface HomeApi {

    /**
     * 用户信息
     *
     * @param uid
     * @return
     */
    @GET("aweme/v1/user/")
    Observable<HttpResult> userInfo(@Query("user_id") String uid);

    /**
     * 播放列表
     *
     * @param type
     * @param max_cursor
     * @param min_cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/feed/")
    Observable<HttpResult> feed(@Query("type") int type,
                                @Query("max_cursor") int max_cursor,
                                @Query("min_cursor") int min_cursor,
                                @Query("count") int count);

    @GET("aweme/v1/comment/list/")
    Observable<HttpResult> commentList(@Query("aweme_id") String aweme_id,
                                       @Query("cursor") int cursor,
                                       @Query("count") int count,
                                       @Query("comment_style") int comment_style);

}
