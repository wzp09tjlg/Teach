package com.wuzp.teach.view.main;


import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.wuzp.teach.R;
import com.wuzp.teach.adapter.MainPageAdapter;
import com.wuzp.teach.base.BaseFragment;
import com.wuzp.teach.base.TechActivity;
import com.wuzp.teach.databinding.ActivityMainBinding;
import com.wuzp.teach.view.main.fragment.BBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TechActivity<ActivityMainBinding,MainPresenter> implements MainView {
    private MainPageAdapter pageAdapter;
    private List<BaseFragment> fragments = new ArrayList<>();
    private String[] titles = {"资讯","新闻","娱乐","阅读","我的"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        BaseFragment info = new BBaseFragment();
        BaseFragment news = new BBaseFragment();
        BaseFragment fun  = new BBaseFragment();
        BaseFragment read = new BBaseFragment();
        BaseFragment user = new BBaseFragment();
        fragments.add(info);
        fragments.add(news);
        fragments.add(fun);
        fragments.add(read);
        fragments.add(user);

        pageAdapter = new MainPageAdapter(getSupportFragmentManager(),fragments,titles);
        binding.pagers.setAdapter(pageAdapter);
        binding.pagers.setOffscreenPageLimit(5);
        binding.pagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//针对fragment 的切换的监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.layoutIndicator.indicatorInfo.setChecked(true);
                        break;
                    case 1:
                        binding.layoutIndicator.indicatorNews.setChecked(true);
                        break;
                    case 2:
                        binding.layoutIndicator.indicatorFun.setChecked(true);
                        break;
                    case 3:
                        binding.layoutIndicator.indicatorRead.setChecked(true);
                        break;
                    case 4:
                        binding.layoutIndicator.indicatorUser.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                binding.layoutIndicator.indicatorInfo.setClickable( state == 0);
                binding.layoutIndicator.indicatorNews.setClickable( state == 0);
                binding.layoutIndicator.indicatorFun.setClickable( state == 0);
                binding.layoutIndicator.indicatorRead.setClickable( state == 0);
                binding.layoutIndicator.indicatorUser.setClickable( state == 0);
            }
        });
        binding.layoutIndicator.layoutMainIndicator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.indicator_info:
                        binding.pagers.setCurrentItem(0,true);
                        break;
                    case R.id.indicator_news:
                        binding.pagers.setCurrentItem(1,true);
                        break;
                    case R.id.indicator_fun:
                        binding.pagers.setCurrentItem(2,true);
                        break;
                    case R.id.indicator_read:
                        binding.pagers.setCurrentItem(3,true);
                        break;
                    case R.id.indicator_user:
                        binding.pagers.setCurrentItem(4,true);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void error(int code, String msg) {

    }
}
