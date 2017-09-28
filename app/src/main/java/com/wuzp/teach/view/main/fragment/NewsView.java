package com.wuzp.teach.view.main.fragment;

import com.wuzp.teach.base.BaseView;
import com.wuzp.teach.network.entity.news.NewsChannelsBean;

import java.util.List;

/**
 * Created by wuzp on 2017/9/28.
 */

public interface NewsView extends BaseView {
    void setNewsChannelData( List<NewsChannelsBean.ChannelBean> data);
}
