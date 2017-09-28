package com.wuzp.teach.view.main.fragment;

import com.wuzp.teach.base.BaseView;
import com.wuzp.teach.network.entity.info.InfosBean;

import java.util.List;

/**
 * Created by wuzp on 2017/9/28.
 */

public interface InfoView extends BaseView {

    public void setData(List<InfosBean.InfoBean> data);
}
