package com.wuzp.teach.view.main.fragment;

import com.wuzp.teach.base.BasePresenter;
import com.wuzp.teach.network.ApiCallback;
import com.wuzp.teach.network.ApiError;
import com.wuzp.teach.network.entity.info.InfosBean;

/**
 * Created by wuzp on 2017/9/28.
 */
public class InfoPresenter extends BasePresenter<InfoView> {
    public InfoPresenter(InfoView view){
        super(view);
    }

    public void start(){
        addSubscription(apiService.getHomeInfo(), new ApiCallback<InfosBean>() {
            @Override
            public void onSuccess(InfosBean model) {
                (mvpView).setData(model.getList());
            }

            @Override
            public void onFailure(ApiError error) {
                mvpView.error(error.getErrorCode(),error.getMessage());
            }
        });
    }
}
