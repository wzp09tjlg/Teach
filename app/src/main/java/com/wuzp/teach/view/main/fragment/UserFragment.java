package com.wuzp.teach.view.main.fragment;

import android.view.View;

import com.wuzp.teach.R;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.databinding.FragmentUserBinding;
import com.wuzp.teach.widget.toast.Msg;

/**
 * Created by wuzp on 2017/9/23.
 */
public class UserFragment extends TechFragment<FragmentUserBinding,UserPresenter> implements UserView ,View.OnClickListener{

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        super.initView();
        binding.layoutTitle.textTitle.setText("我的");
        binding.layoutTitle.imgTitleBack.setVisibility(View.INVISIBLE);
        binding.layoutTitle.imgTitleMenu.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        binding.layoutPurse.setOnClickListener(this);
        binding.layoutCard.setOnClickListener(this);
        binding.layoutFav.setOnClickListener(this);
        binding.layoutLink.setOnClickListener(this);
        binding.layoutFlag.setOnClickListener(this);
        binding.layoutSetting.setOnClickListener(this);
    }

    @Override
    public void error(int code, String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_purse:
                Msg.getInstance().show("钱包");
                break;
            case R.id.layout_card:
                Msg.getInstance().show("卡包");
                break;
            case R.id.layout_fav:
                Msg.getInstance().show("喜欢");
                break;
            case R.id.layout_link:
                Msg.getInstance().show("连接");
                break;
            case R.id.layout_flag:
                Msg.getInstance().show("标记");
                break;
            case R.id.layout_setting:
                Msg.getInstance().show("设置");
                break;
        }
    }
}
