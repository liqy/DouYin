package com.liqy.douyin.shoot;

import com.liqy.douyin.common.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 拍摄页面
 * Created by liqy on 2018/1/28.
 */

public interface ShootApi {

    /**
     * 音乐类型集合
     *
     * @param cursor
     * @param count  1024
     * @return
     */
    @GET("aweme/v1/music/collection/")
    Observable<HttpResult> musicCollection(@Query("cursor") int cursor,
                                           @Query("count") int count);

    /**
     * 热门音乐
     *
     * @param cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/hot/music/")
    Observable<HttpResult> hotMusic(@Query("cursor") int cursor,
                                    @Query("count") int count);

    /**
     * 我收藏音乐列表
     *
     * @param cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/user/music/collect/")
    Observable<HttpResult> musicCollect(@Query("cursor") int cursor,
                                        @Query("count") int count);

    /**
     * 收藏动作
     *
     * @param music_id
     * @param type
     * @return
     */
    @GET("aweme/v1/music/collect/")
    Observable<HttpResult> musicCollect(@Query("music_id") String music_id,
                                        @Query("type") int type);

    /**
     * 类型音乐列表
     *
     * @param mc_id
     * @param cursor
     * @param count
     * @return
     */
    @GET("aweme/v1/music/list/")
    Observable<HttpResult> musicList(@Query("mc_id") int mc_id,
                                     @Query("cursor") int cursor,
                                     @Query("count") int count);

    /**
     * 尬音机
     *
     * @return
     */
    @GET("/aweme/v1/bodydance/resource/")
    Observable<HttpResult> bodydance();

    /**
     * 音乐详情
     * @param music_id
     * @param aweme_id
     * @return
     */
    @GET("/aweme/v1/music/detail/")
    Observable<HttpResult> musicDetail(@Query("music_id") String music_id, @Query("aweme_id") String aweme_id);


    /**
     * 上面接口一起在详情页面
     * @param music_id
     * @param cursor
     * @param count
     * @return
     */
    @GET("/aweme/v1/music/aweme/")
    Observable<HttpResult> musicAweme(@Query("music_id") String music_id,
                                      @Query("cursor") int cursor,
                                      @Query("count") int count
                                      );

    /**
     * 尬舞排行榜
     * @param music_id
     * @return
     */
    @GET("/aweme/v1/bodydance/rank/detail/")
    Observable<HttpResult> bodydanceRank(@Query("music_id") String music_id);


    /**
     * 道具
     * @return
     */
    @GET("/aweme/v1/face/sticker/")
    Observable<HttpResult> faceSticker();

}
