package com.wuzp.teach.view.webDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.wuzp.teach.R;
import com.wuzp.teach.base.BaseApp;
import com.wuzp.teach.base.TechActivity;
import com.wuzp.teach.databinding.ActivityWebDetailBinding;
import com.wuzp.teach.receiver.NetChangeObserver;
import com.wuzp.teach.receiver.NetworkStateReceiver;
import com.wuzp.teach.utils.PixelUtil;
import com.wuzp.teach.utils.network.NetworkUtil;
import com.wuzp.teach.widget.toast.Msg;
import com.wuzp.teach.widget.webview.TechWebView;

/**
 * Created by wuzp on 2017/9/23.
 */
public class WebDetailActivity extends TechActivity<ActivityWebDetailBinding,WebDetailPresenter> implements View.OnClickListener, WebDetailView {
    private String mTitle = "";
    private String mUrl = "";
    private boolean isload=false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_detail;
    }

    @Override
    protected WebDetailPresenter createPresenter() {
        return new WebDetailPresenter(this);
    }

    private void getData(){
        Intent intent = getIntent();
        try {
            Bundle bundle = intent.getExtras();
            mTitle = bundle.getString("TITLE","");
            mUrl   = bundle.getString("URL","");
        }catch (Exception e){}
    }

    @Override
    protected void initView() {
        super.initView();
        getData();
        binding.layoutTitle.imgTitleBack.setVisibility(View.VISIBLE);
        binding.layoutTitle.imgTitleMenu.setVisibility(View.VISIBLE);
        binding.layoutTitle.textTitle.setText(mTitle);

        initWebSetting();
    }

    @Override
    protected void initData() {
        super.initData();
        binding.layoutTitle.imgTitleBack.setOnClickListener(this);
        binding.layoutTitle.imgTitleMenu.setOnClickListener(this);
        loadUrl();
    }

    private void initWebSetting(){
        binding.web.setStatusInterface(statusInterface);
        binding.web.getSettings().setJavaScriptEnabled(true);
        //        支持特殊的javascript脚本语句
        binding.web.setWebChromeClient(new WebChromeClient());
        //                支持javascript脚本语句
        //取消滚动条
        binding.web.setScrollBarStyle(binding.web.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        //        binding.web.requestFocus();
        //                binding.web.getSettings().setBlockNetworkImage(true);
        //        binding.web.getSettings().setBlockNetworkLoads(false);
        binding.web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        binding.web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        binding.web.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        binding.web.getSettings().setDatabaseEnabled(true);
        //开启 Application Caches 功能
        binding.web.getSettings().setAppCacheEnabled(true);
        //webview屏幕适配
        binding.web.getSettings().setLoadWithOverviewMode(true);
        binding.web.getSettings().setUseWideViewPort(true);

        //注册网 //动态注册网络监听络变化的观察者
        NetworkStateReceiver.registerNetworkStateReceiver(BaseApp.gContext);
        NetworkStateReceiver.registerObserver(observer);
        //binding.refresh.setEnabled(false);
        binding.refresh.setProgressViewOffset(false, 0, PixelUtil.dp2px(50));
        //binding.refresh.setDistanceToTriggerSync(Pix
        // elUtil.dp2px(100));
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUrl = null;
                if (isload) {
                    binding.refresh.setRefreshing(true);
                    return;
                }
                binding.web.loadUrl(binding.web.getUrl());
                isload = true;
            }
        });
    }

    private void loadUrl(){
        if(!TextUtils.isEmpty(mUrl)){
            binding.web.loadUrl(mUrl);
        }else{
            Msg.getInstance().show("地址为空");
        }
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.img_title_back:
             finish();
             break;
         case R.id.img_title_menu:

             break;
     }
    }

    @Override
    public void error(int code, String msg) {

    }

    private NetChangeObserver observer = new NetChangeObserver() {
        @Override
        public void onConnect(NetworkUtil.NetType type) {
            super.onConnect(type);
            if (binding.web == null) {
                return;
            }
            binding.web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }

        @Override
        public void onDisConnect() {
            super.onDisConnect();
            if (binding.web == null) {
                return;
            }
            binding.web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    };

    private TechWebView.OnWebViewStatusInterface statusInterface = new TechWebView.OnWebViewStatusInterface() {
        @Override
        public void onPageLoadFinished() {
            isload = false;
            binding.refresh.setRefreshing(false);
        }

        @Override
        public void onPageStartLoad() {
            isload = false;
            binding.refresh.setRefreshing(false);
        }

        @Override
        public void onPageLoadError() {
            isload = false;
            binding.refresh.setRefreshing(false);
        }
    };
}
