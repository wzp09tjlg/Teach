package com.wuzp.teach.network;

import com.wuzp.teach.network.entity.base.HttpBase;
import com.wuzp.teach.network.entity.info.InfosBean;
import com.wuzp.teach.network.entity.news.NewsChannelsBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by wuzp on 2017/9/17.
 * 定义Retrofit的请求地址
 */
public interface ApiService {

    /*******************************************/
    // 首页模块的地址
    /*******************************************/
    //资讯
    @GET(ApiFinal.URL_HOME_INFO)
    Flowable<HttpBase<InfosBean>> getHomeInfo();

    //地域新闻
    @GET(ApiFinal.URL_HOME_NEW_CHANNEL)
    Flowable<HttpBase<NewsChannelsBean>> getHomeNewsChannel();

    //新闻
    //娱乐
    //读书
    //我的
}
