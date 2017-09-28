package com.wuzp.teach.view.main.fragment;

import com.wuzp.teach.base.BasePresenter;
import com.wuzp.teach.base.BaseView;
import com.wuzp.teach.network.ApiCallback;
import com.wuzp.teach.network.ApiError;
import com.wuzp.teach.network.entity.news.NewsChannelsBean;

/**
 * Created by wuzp on 2017/9/28.
 */
public class NewsPresenter extends BasePresenter<BaseView> {
    public NewsPresenter(BaseView view){
        super(view);
    }

    public void start(){
        addSubscription(apiService.getHomeNewsChannel(), new ApiCallback<NewsChannelsBean>() {
            @Override
            public void onSuccess(NewsChannelsBean model) {
                ((NewsView)mvpView).setNewsChannelData(model.getChannelList());
            }

            @Override
            public void onFailure(ApiError error) {
                mvpView.error(error.getErrorCode(),error.getMessage());
            }
        });
    }
}

