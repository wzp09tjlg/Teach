package com.wuzp.teach.view.main.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.wuzp.teach.R;
import com.wuzp.teach.adapter.MainPageAdapter;
import com.wuzp.teach.base.BaseFragment;
import com.wuzp.teach.base.TechFragment;
import com.wuzp.teach.databinding.FragmentFunBinding;
import com.wuzp.teach.view.entertaiment.FunnyPicFragment;
import com.wuzp.teach.view.entertaiment.JokeTextFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuzp on 2017/9/28.
 */
public class FunFragment extends TechFragment<FragmentFunBinding,FunPresenter> implements FunView,View.OnClickListener {
    private List<BaseFragment> fragments = new ArrayList<>();
    private MainPageAdapter pageAdapter;
    private int mCurrentIndex = 0;

    @Override
    protected FunPresenter createPresenter() {
        return new FunPresenter(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fun;
    }

    @Override
    protected void initView() {
        super.initView();
        binding.layoutTitle.imgTitleBack.setVisibility(View.INVISIBLE);
        binding.layoutTitle.imgTitleMenu.setVisibility(View.INVISIBLE);
        binding.layoutTitle.textTitle.setText("娱乐");

        binding.pagesFun.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    mCurrentIndex = 0;
                    binding.layoutSort.textJoke.setChecked(true);
                }else if(position == 1){
                    mCurrentIndex = 1;
                    binding.layoutSort.textFunnyPic.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state){
                binding.layoutSort.textJoke.setClickable(state == 0);
                binding.layoutSort.textFunnyPic.setClickable(state == 0);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        JokeTextFragment fragmentJoke = new JokeTextFragment();
        FunnyPicFragment fragmentFunny = new FunnyPicFragment();
        fragments.add(fragmentJoke);
        fragments.add(fragmentFunny);

        pageAdapter = new MainPageAdapter(getFragmentManager(),fragments,null);
        binding.pagesFun.setAdapter(pageAdapter);

        binding.layoutSort.textJoke.setOnClickListener(this);
        binding.layoutSort.textFunnyPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_joke:
                if(mCurrentIndex != 0){
                    mCurrentIndex = 0;
                    binding.pagesFun.setCurrentItem(0,true);
                    binding.layoutSort.textJoke.setChecked(true);
                }
                break;
            case R.id.text_funny_pic:
                if(mCurrentIndex != 1){
                    mCurrentIndex = 1;
                    binding.pagesFun.setCurrentItem(1,true);
                    binding.layoutSort.textFunnyPic.setChecked(true);
                }
                break;

        }
    }

    @Override
    public void error(int code, String msg) {}
}
