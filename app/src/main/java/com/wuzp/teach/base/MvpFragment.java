package com.wuzp.teach.base;

import android.content.Context;

/**
 * Created by wuzp on 2017/9/27.
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    private P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter != null){
            presenter.detachView();
        }
    }

    protected abstract P createPresenter();
}
