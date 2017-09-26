package com.wuzp.teach.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuzp on 2017/9/27.
 */
public abstract class TechFragment<B extends ViewDataBinding,P extends BasePresenter> extends BaseFragment {

    protected B binding;
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter  = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,layoutId(),container,true);

        onCreateView();
        initView();
        initData();
        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter != null){
            presenter.detachView();
        }
    }

    protected abstract P createPresenter();

    protected abstract int layoutId();

    protected void onCreateView(){}

    protected void initView(){}

    protected void initData(){}

}