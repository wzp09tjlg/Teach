package com.wuzp.teach.base;

import android.app.Application;
import android.content.Context;

import com.wuzp.teach.database.service.DBService;
import com.wuzp.teach.utils.PreferenceUtil;

/**
 * Created by wuzp on 2017/9/27.
 */
public class BaseApp extends Application {

    public static Context gContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initGlobalVar();
    }

    private void initGlobalVar(){
        gContext = this;

        PreferenceUtil.init(gContext); //初始化Sp
        DBService.init(gContext);//初始化数据库

    }
}

