package com.wuzp.teach.view.entertaiment;


import com.wuzp.teach.base.BasePresenter;
import com.wuzp.teach.network.ApiCallback;
import com.wuzp.teach.network.ApiError;
import com.wuzp.teach.network.entity.entertaiment.EntertainmentBean;

/**
 * Created by wuzp on 2017/9/24.
 */
public class FunnyPicPresenter extends BasePresenter<FunnyPicView> {
    public FunnyPicPresenter(FunnyPicView view) {
        super(view);
    }

    public void start() {
        addSubscription(apiService.getHomeFunnyPic(), new ApiCallback<EntertainmentBean>() {
            @Override
            public void onSuccess(EntertainmentBean model) {
                mvpView.setFunnyPicData(model.getContentlist());
            }

            @Override
            public void onFailure(ApiError error) {
                mvpView.error(error.getErrorCode(),error.getMessage());
            }
        });
    }
}
