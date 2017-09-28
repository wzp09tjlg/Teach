package com.wuzp.teach.view.main.fragment;

import com.wuzp.teach.R;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.databinding.FragmentBaseBinding;

/**
 * Created by wuzp on 2017/9/28.
 */
public class BBaseFragment extends TechFragment<FragmentBaseBinding,BBasePresenter>  implements BBaseView {

    @Override
    protected BBasePresenter createPresenter() {
        return new BBasePresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_base;
    }

    @Override
    public void error(int code, String msg) {

    }
}
